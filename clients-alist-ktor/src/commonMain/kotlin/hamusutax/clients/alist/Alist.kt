package hamusutax.clients.alist

import hamusutax.clients.alist.model.payload.*
import hamusutax.clients.alist.model.response.*
import hamusutax.clients.alist.utils.AlistException
import hamusutax.clients.alist.utils.checkCode
import hamusutax.io.path.extension
import hamusutax.io.path.readByteArray
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.contentType
import io.ktor.http.defaultForFileExtension
import io.ktor.http.encodeURLPath
import io.ktor.http.encodeURLQueryComponent
import io.ktor.http.quote
import io.ktor.utils.io.ByteReadChannel
import kotlinx.io.files.Path
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

class Alist(
    private val client: HttpClient,
    private val baseUrl: String = "http://127.0.0.1:5244"
) {
    private var authorization = ""

    private suspend fun alistGet(url: String, params: Map<String, String> = emptyMap()): JsonElement? {
        val response = client.get(baseUrl + url) {
            url {
                params.forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }
            headers {
                append(HttpHeaders.Authorization, authorization)
            }
        }
        return response.body<Api<JsonElement>>().checkCode()
    }

    private suspend inline fun <reified T> alistGetAs(url: String, params: Map<String, String> = emptyMap()) =
        alistGet(url, params)!!.let {
            Json.decodeFromJsonElement<T>(it)
        }

    private suspend inline fun <reified T> alistPost(url: String, params: Map<String, String> = emptyMap(), payload: T? = null): JsonElement? {
        val response = client.post(baseUrl + url) {
            url {
                params.forEach { (key, value) ->
                    parameters.append(key, value)
                }
            }
            headers {
                append(HttpHeaders.Authorization, authorization)
            }
            payload?.let {
                setBody(it)
            }
        }
        return response.body<Api<JsonElement>>().checkCode()
    }

    private suspend fun alistPost(url: String, params: Map<String, String> = emptyMap()) =
        alistPost<JsonElement>(url, params)

    private suspend inline fun <reified T, reified R> alistPostAs(url: String, params: Map<String, String> = emptyMap(), payload: T? = null) =
        alistPost(url, params, payload)!!.let {
            Json.decodeFromJsonElement<R>(it)
        }

    private suspend inline fun <reified T> alistPostAs(url: String) =
        alistPostAs<JsonElement, T>(url)

    /**
     * 尝试下载文件
     *
     * @param path 需要下载的文件路径
     * @param sign 下载签名，由 [fsList] 获取（[FsListResponse.Content.sign]）
     * @return 服务器提供的文件名与字节序列
     */
    suspend fun download(path: String, sign: String): ByteReadChannel {
        require(path.isNotEmpty() && (path[0] == '/' || path[0] == '\\'))
        val response = client.get("$baseUrl/d$path".replace("\\", "/").encodeURLPath()) {
            url {
                parameters.append("sign", sign)
            }
        }
        response.headers["Content-Disposition"] ?: run {
            response.body<Api<JsonElement>>().checkCode()
            throw AlistException()
        }
        return response.bodyAsChannel()
    }

    /**
     * POST /api/auth/login
     *
     * Token 获取
     *
     * 获取某个用户的临时 JWT Token
     */
    suspend fun authLogin(username: String, password: String, otpCode: String = "") =
        authLogin(AuthLoginPayload(username, password, otpCode))

    suspend fun authLogin(payload: AuthLoginPayload) =
        alistPostAs<AuthLoginPayload, AuthLoginResponse>("/api/auth/login", payload = payload)
            .also { authorization = it.token }

    /**
     * POST /api/auth/login/hash
     *
     * Token 获取 Hash
     *
     * 获取某个用户的临时 JWT Token，传入的密码需要在添加-https://github.com/alist-org/alist后缀后再进行sha256
     */
    @Deprecated("返回结果不符合预期", level = DeprecationLevel.WARNING)
    suspend fun authLoginHash(username: String, password: String, otpCode: String = "") =
        authLoginHash(AuthLoginHashPayload(username, password, otpCode))

    @Deprecated("返回结果不符合预期", level = DeprecationLevel.WARNING)
    suspend fun authLoginHash(payload: AuthLoginHashPayload) =
        alistPostAs<AuthLoginHashPayload, AuthLoginHashResponse>("/api/auth/login/hash", payload = payload)
            .also { authorization = it.token }

    /**
     * POST /api/auth/2fa/generate
     *
     * 生成 2FA 密钥
     */
    suspend fun auth2faGenerate(): Auth2faGenerateResponse =
        alistPostAs("/api/auth/2fa/generate")

    /**
     * POST /api/auth/2fa/verify
     *
     * 验证 2FA Code
     */
    suspend fun auth2faVerify(code: String, secret: String) =
        auth2faVerify(Auth2FAVerifyPayload(code, secret))

    suspend fun auth2faVerify(payload: Auth2FAVerifyPayload) =
        alistPost("/api/auth/2fa/verify", payload = payload)

    /**
     * GET /api/me
     *
     * 获取当前用户信息
     */
    suspend fun me(): MeResponse =
        alistGetAs("/api/me")

    /**
     * POST /api/fs/mkdir
     *
     * 新建文件夹
     */
    suspend fun fsMkdir(path: String) =
        fsMkdir(FsMkdirPayload(path))

    suspend fun fsMkdir(payload: FsMkdirPayload) =
        alistPost("/api/fs/mkdir", payload = payload)

    /**
     * POST /api/fs/rename
     *
     * 重命名文件
     */
    suspend fun fsRename(name: String, path: String) =
        fsRename(FsRenamePayload(name, path))

    suspend fun fsRename(payload: FsRenamePayload) =
        alistPost("/api/fs/rename", payload = payload)

    /**
     * PUT /api/fs/form
     *
     * 表单上传文件。当目标位置已存在文件时会直接覆盖
     *
     * @param file 上传文件的路径
     * @param target 文件存放在 Alist 中的位置，含文件名
     */
    suspend fun fsForm(file: String, target: String, asTask: Boolean = false): Boolean {
        val path = Path(file)
        val bytes = path.readByteArray()
        val response = client.put("$baseUrl/api/fs/form") {
            headers {
                append(HttpHeaders.Authorization, authorization)
                append("File-Path", target.encodeURLQueryComponent())
                append("As-Task", asTask.toString())
            }
            contentType(ContentType.MultiPart.FormData)
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("file".quote(), bytes, Headers.build {
                            append(HttpHeaders.ContentType, ContentType.defaultForFileExtension(path.extension))
                            append(HttpHeaders.ContentDisposition, "filename=${path.name.quote()}")
                        })
                    }
                )
            )
        }
        response.body<Api<FsFormResponse>>().checkCode()
        return true
    }

    /**
     * POST /api/fs/list
     *
     * 列出文件目录
     */
    suspend fun fsList(
        path: String = "/",
        password: String = "",
        page: Int = 1,
        perPage: Int = 30,
        refresh: Boolean = false
    ) =
        fsList(FsListPayload(path, password, page, perPage, refresh))

    suspend fun fsList(payload: FsListPayload): FsListResponse =
        alistPostAs("/api/fs/list", payload = payload)

    /**
     * POST /api/fs/get
     *
     * 获取某个文件/目录信息
     */
    suspend fun fsGet(
        path: String = "/",
        password: String = "",
        page: Int = 1,
        perPage: Int = 30,
        refresh: Boolean = false
    ) =
        fsGet(FsGetPayload(path, password, page, perPage, refresh))

    suspend fun fsGet(payload: FsGetPayload): FsGetResponse =
        alistPostAs("/api/fs/get", payload = payload)

    /**
     * POST /api/fs/search
     *
     * 搜索文件或文件夹
     *
     * @param parent 要搜索的文件夹
     * @param keywords 搜索关键词
     * @param scope 搜索类型，0-全部 1-文件夹 2-文件
     * @param page 搜索结果页码
     * @param perPage 每页数目
     * @param password 要搜索的文件夹密码
     */
    suspend fun fsSearch(
        parent: String,
        keywords: String,
        scope: Int = 0,
        page: Int = 1,
        perPage: Int = 100,
        password: String = ""
    ) =
        fsSearch(FsSearchPayload(parent, keywords, scope, page, perPage, password))

    suspend fun fsSearch(payload: FsSearchPayload): FsSearchResponse =
        alistPostAs("/api/fs/search", payload = payload)

    /**
     * POST /api/fs/dirs
     *
     * 获取目录
     */
    suspend fun fsDirs(
        path: String = "/",
        password: String = "",
        page: Int = 1,
        perPage: Int = 30,
        refresh: Boolean = false
    ) =
        fsDirs(FsDirsPayload(path, password, page, perPage, refresh))

    suspend fun fsDirs(payload: FsDirsPayload): List<FsDirsResponseItem> =
        alistPostAs("/api/fs/dirs", payload = payload)

    /**
     * POST /api/fs/batch_rename
     *
     * 批量重命名
     */
    suspend fun fsBatchRename(srcDir: String, vararg renameObjects: FsBatchRenamePayload.RenameObject) =
        fsBatchRename(FsBatchRenamePayload(srcDir, renameObjects.toList()))

    suspend fun fsBatchRename(payload: FsBatchRenamePayload) =
        alistPost("/api/fs/batch_rename", payload = payload)

    /**
     * POST /api/fs/regex_rename
     *
     * 正则重命名
     */
    suspend fun fsRegexRename(srcDir: String, vararg renameObjects: FsRegexRenamePayload.RenameObject) =
        fsRegexRename(FsRegexRenamePayload(srcDir, renameObjects.toList()))

    suspend fun fsRegexRename(payload: FsRegexRenamePayload) =
        alistPost("/api/fs/regex_rename", payload = payload)

    /**
     * POST /api/fs/move
     *
     * 移动文件
     */
    suspend fun fsMove(srcDir: String, dstDir: String, vararg names: String) =
        fsMove(FsMovePayload(srcDir, dstDir, names.toList()))

    suspend fun fsMove(payload: FsMovePayload) =
        alistPost("/api/fs/move", payload = payload)

    /**
     * POST /api/fs/recursive_move
     *
     * 聚合移动
     */
    suspend fun fsRecursiveMove(srcDir: String, dstDir: String) =
        fsRecursiveMove(FsRecursiveMovePayload(srcDir, dstDir))

    suspend fun fsRecursiveMove(payload: FsRecursiveMovePayload) =
        alistPost("/api/fs/recursive_move", payload = payload)

    /**
     * POST /api/fs/copy
     *
     * 复制文件
     */
    suspend fun fsCopy(srcDir: String, dstDir: String, vararg names: String) =
        fsCopy(FsCopyPayload(srcDir, dstDir, names.toList()))

    suspend fun fsCopy(payload: FsCopyPayload) =
        alistPost("/api/fs/copy", payload = payload)

    /**
     * POST /api/fs/remove
     *
     * 删除文件或文件夹
     */
    suspend fun fsRemove(dir: String, vararg names: String) =
        fsRemove(FsRemovePayload(names.toList(), dir))

    suspend fun fsRemove(payload: FsRemovePayload) =
        alistPost("/api/fs/remove", payload = payload)

    /**
     * POST /api/fs/remove_empty_directory
     *
     * 删除空文件夹
     *
     * @param srcDir 要删除的文件夹路径
     */
    suspend fun fsRemoveEmptyDirectory(srcDir: String) =
        fsRemoveEmptyDirectory(
            FsRemoveEmptyDirectoryPayload(
                srcDir
            )
        )

    suspend fun fsRemoveEmptyDirectory(payload: FsRemoveEmptyDirectoryPayload) =
        alistPost("/api/fs/remove_empty_directory", payload = payload)

    /**
     * PUT /api/fs/put
     *
     * 流式上传文件
     *
     * @param file 上传文件的路径
     * @param target 文件存放在 Alist 中的位置，含文件名
     */
    suspend fun fsPut(file: String, target: String, asTask: Boolean = false): Boolean {
        val path = Path(file)
        val bytes = path.readByteArray()
        val response = client.put("$baseUrl/api/fs/put") {
            headers {
                append(HttpHeaders.Authorization, authorization)
                append("File-Path", target.encodeURLQueryComponent())
                append("As-Task", asTask.toString())
            }
            contentType(ContentType.defaultForFileExtension(path.extension))
            setBody(bytes)
        }
        response.body<Api<FsPutResponse>>().checkCode()
        return true
    }

    /**
     * POST /api/fs/add_aria2
     *
     * 添加 aria2 下载
     */
    suspend fun fsAddAria2(path: String, vararg urls: String) =
        fsAddAria2(FsAddAria2Payload(urls.toList(), path))

    suspend fun fsAddAria2(payload: FsAddAria2Payload) =
        alistPost("/api/fs/add_aria2", payload = payload)

    /**
     * POST /api/fs/add_qbit
     *
     * 添加 qBittorrent 下载
     */
    suspend fun fsAddQbit(path: String, vararg urls: String) =
        fsAddQbit(FsAddQbitPayload(urls.toList(), path))

    suspend fun fsAddQbit(payload: FsAddQbitPayload) =
        alistPost("/api/fs/add_qbit", payload = payload)

    /**
     * GET /ping
     *
     * 连通性 ping 检测
     */
    suspend fun ping() = alistGet("/ping")

    /**
     * GET /api/public/settings
     *
     * 获取站点设置
     */
    suspend fun publicSettings(): PublicSettingsResponse =
        alistGetAs("/api/public/settings")

    /**
     * GET /api/admin/user/list
     *
     * 列出所有用户
     */
    suspend fun adminUserList(): AdminUserListResponse =
        alistGetAs("/api/admin/user/list")

    /**
     * GET /api/admin/user/get
     *
     * 列出某个用户
     */
    suspend fun adminUserGet(id: String): AdminUserGetResponse =
        alistGetAs("/api/admin/user/get", mapOf("id" to id))

    /**
     * POST /api/admin/user/create
     *
     * 新建用户
     */
    suspend fun adminUserCreate(payload: AdminUserCreatePayload) =
        alistPost("/api/admin/user/create", payload = payload)

    suspend fun adminUserCreate(
        id: Int,
        username: String,
        password: String,
        basePath: String = "/",
        role: Int = 0,
        permission: Int = 60,
        disable: Boolean = false,
        ssoId: String = ""
    ) =
        adminUserCreate(AdminUserCreatePayload(id, username, password, basePath, role, permission, disable, ssoId))

    /**
     * POST /api/admin/user/update
     *
     * 更新用户信息
     */
    suspend fun adminUserUpdate(payload: AdminUserUpdatePayload) =
        alistPost("/api/admin/user/update", payload = payload)

    suspend fun adminUserUpdate(
        id: Int,
        username: String,
        password: String,
        basePath: String = "/",
        role: Int = 0,
        permission: Int = 60,
        disable: Boolean = false,
        ssoId: String = ""
    ) =
        adminUserUpdate(AdminUserUpdatePayload(id, username, password, basePath, role, permission, disable, ssoId))

    /**
     * POST /api/admin/user/cancel_2fa
     *
     * 取消某个用户的两步验证
     */
    suspend fun adminUserCancel2fa(id: String) =
        alistPost("/api/admin/user/cancel_2fa", mapOf("id" to id))

    /**
     * POST /api/admin/user/delete
     *
     * 删除用户
     */
    suspend fun adminUserDelete(id: String) =
        alistPost("/api/admin/user/delete", mapOf("id" to id))

    /**
     * POST /api/admin/meta/list
     *
     * 列出元信息
     */
    suspend fun adminMetaList(page: String, perPage: String): AdminMetaListResponse =
        alistGetAs("/api/admin/meta/list", mapOf("page" to page, "perPage" to perPage))

    /**
     * POST /api/admin/meta/get
     *
     * 获取元信息
     */
    suspend fun adminMetaGet(id: String): AdminMetaGetResponse =
        alistGetAs("/api/admin/meta/get", mapOf("id" to id))

    /**
     * POST /api/admin/meta/create
     *
     * 新增元信息
     *
     * @param id 用户 ID
     * @param path 路径
     * @param password 密码
     * @param pSub 密码是否应用到子文件夹
     * @param write 是否开启写入
     * @param wSub 开启写入是否应用到子文件夹
     * @param hide 隐藏
     * @param hSub 隐藏是否应用到子文件夹
     * @param readme 说明
     * @param rSub 说明是否应用到子文件夹
     */
    suspend fun adminMetaCreate(
        id: Int,
        path: String,
        password: String,
        pSub: Boolean = false,
        write: Boolean = false,
        wSub: Boolean = false,
        hide: String = "",
        hSub: Boolean = false,
        readme: String = "",
        rSub: Boolean = false
    ) =
        adminMetaCreate(AdminMetaCreatePayload(id, path, password, pSub, write, wSub, hide, hSub, readme, rSub))

    suspend fun adminMetaCreate(payload: AdminMetaCreatePayload) =
        alistPost("/api/admin/meta/create", payload = payload)

    /**
     * POST /api/admin/meta/update
     *
     * 更新元信息
     *
     * @param id 用户 ID
     * @param path 路径
     * @param password 密码
     * @param pSub 密码是否应用到子文件夹
     * @param write 是否开启写入
     * @param wSub 开启写入是否应用到子文件夹
     * @param hide 隐藏
     * @param hSub 隐藏是否应用到子文件夹
     * @param readme 说明
     * @param rSub 说明是否应用到子文件夹
     */
    suspend fun adminMetaUpdate(
        id: Int,
        path: String,
        password: String,
        pSub: Boolean = false,
        write: Boolean = false,
        wSub: Boolean = false,
        hide: String = "",
        hSub: Boolean = false,
        readme: String = "",
        rSub: Boolean = false
    ) =
        adminMetaUpdate(AdminMetaUpdatePayload(id, path, password, pSub, write, wSub, hide, hSub, readme, rSub))

    suspend fun adminMetaUpdate(payload: AdminMetaUpdatePayload) =
        alistPost("/api/admin/meta/update", payload = payload)

    /**
     * POST /api/admin/meta/delete
     *
     * 删除元信息
     */
    suspend fun adminMetaDelete(id: String) =
        alistPost("/api/admin/meta/delete", mapOf("id" to id))

    /**
     * GET /api/admin/driver/names
     *
     * 列出驱动名列表
     */
    suspend fun adminDriverNames(): List<String> =
        alistGetAs("/api/admin/driver/names")

    /**
     * GET /api/admin/driver/info
     *
     * 列出特定驱动信息
     */
    suspend fun adminDriverInfo(driver: String): AdminDriverInfoResponse =
        alistGetAs("/api/admin/driver/info", mapOf("driver" to driver))

    /**
     * GET /api/admin/storage/list
     *
     * 列出存储列表
     */
    suspend fun adminStorageList(page: String, perPage: String): AdminStorageListResponse =
        alistGetAs("/api/admin/storage/list", mapOf("page" to page, "per_page" to perPage))

    /**
     * POST /api/admin/storage/enable
     *
     * 启用存储
     */
    suspend fun adminStorageEnable(id: String) =
        alistPost("/api/admin/storage/enable", mapOf("id" to id))

    /**
     * POST /api/admin/storage/disable
     *
     * 禁用存储
     */
    suspend fun adminStorageDisable(id: String) =
        alistPost("/api/admin/storage/disable", mapOf("id" to id))

    /**
     * POST /api/admin/storage/create
     *
     * 创建存储
     *
     * @param mountPath 挂载路径
     * @param order 排序
     * @param remark 备注名
     * @param cacheExpiration 缓存过期时间
     * @param webProxy Web 代理
     * @param webdavPolicy WebDAV 策略
     * @param downProxyUrl 下载代理
     * @param extractFolder 提取目录
     * @param enableSign 启用签名
     * @param driver 驱动
     * @param orderBy 排序
     * @param orderDirection 排序方向
     * @param addition 额外信息
     */
    suspend fun adminStorageCreate(
        mountPath: String,
        order: Int,
        remark: String,
        cacheExpiration: Int,
        webProxy: Boolean,
        webdavPolicy: String,
        downProxyUrl: String,
        extractFolder: String,
        enableSign: Boolean,
        driver: String,
        orderBy: String,
        orderDirection: String,
        addition: String
    ): AdminStorageCreateResponse =
        adminStorageCreate(AdminStorageInfo(mountPath, order, remark, cacheExpiration, webProxy, webdavPolicy, downProxyUrl, extractFolder, enableSign, driver, orderBy, orderDirection, addition))

    suspend fun adminStorageCreate(payload: AdminStorageInfo): AdminStorageCreateResponse =
        alistPostAs("/api/admin/storage/create", payload = payload)

    /**
     * POST /api/admin/storage/update
     *
     * 更新存储
     *
     * @param mountPath 挂载路径
     * @param order 排序
     * @param remark 备注名
     * @param cacheExpiration 缓存过期时间
     * @param webProxy Web 代理
     * @param webdavPolicy WebDAV 策略
     * @param downProxyUrl 下载代理
     * @param extractFolder 提取目录
     * @param enableSign 启用签名
     * @param driver 驱动
     * @param orderBy 排序
     * @param orderDirection 排序方向
     * @param addition 额外信息
     */
    suspend fun adminStorageUpdate(
        mountPath: String,
        order: Int,
        remark: String,
        cacheExpiration: Int,
        webProxy: Boolean,
        webdavPolicy: String,
        downProxyUrl: String,
        extractFolder: String,
        enableSign: Boolean,
        driver: String,
        orderBy: String,
        orderDirection: String,
        addition: String
    ): AdminStorageCreateResponse =
        adminStorageUpdate(AdminStorageInfo(mountPath, order, remark, cacheExpiration, webProxy, webdavPolicy, downProxyUrl, extractFolder, enableSign, driver, orderBy, orderDirection, addition))

    suspend fun adminStorageUpdate(payload: AdminStorageInfo): AdminStorageCreateResponse =
        alistPostAs("/api/admin/storage/update", payload = payload)

    /**
     * GET /api/admin/storage/get
     *
     * 查询指定存储信息
     */
    suspend fun adminStorageGet(id: String): AdminStorageGetResponse =
        alistGetAs("/api/admin/storage/get", mapOf("id" to id))

    /**
     * POST /api/admin/storage/delete
     *
     * 删除指定存储
     */
    suspend fun adminStorageDelete(id: String) =
        alistPost("/api/admin/storage/delete", mapOf("id" to id))

    /**
     * POST /api/admin/storage/load_all
     *
     * 重新加载所有存储
     */
    suspend fun adminStorageLoadAll() =
        alistPost("/api/admin/storage/load_all")
}
