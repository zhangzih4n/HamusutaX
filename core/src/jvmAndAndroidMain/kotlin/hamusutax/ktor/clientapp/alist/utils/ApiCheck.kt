package hamusutax.ktor.clientapp.alist.utils

import hamusutax.ktor.clientapp.alist.model.response.Api

fun <T> Api<T>.checkCode() =
    when (code) {
        200 -> data
        401 -> throw hamusutax.ktor.clientapp.alist.utils.Alist401Exception(message)
        403 -> throw hamusutax.ktor.clientapp.alist.utils.Alist403Exception(message)
        500 -> throw hamusutax.ktor.clientapp.alist.utils.Alist500Exception(message)
        else -> throw hamusutax.ktor.clientapp.alist.utils.AlistCodeException(code, message)
    }

// Token 格式错误：{"code":401,"message":"that's not even a token","data":null}
// Token 失效：{"code":401,"message":"couldn't handle this token","data":null}
// 路径需要密码：{"code":403,"message":"password is incorrect or you have no permission","data":null}
// 获取路径不存在：{"code":500,"message":"failed get storage: can't find storage with rawPath: /t","data":null}
// 上传路径不存在：{"code":500,"message":"http: no such file","data":null}
open class AlistException(override val message: String? = null): Throwable()
open class AlistCodeException(val code: Int, override val message: String? = null): hamusutax.ktor.clientapp.alist.utils.AlistException("code: $code >>> $message")
class Alist401Exception(override val message: String?): hamusutax.ktor.clientapp.alist.utils.AlistCodeException(401, message)
class Alist403Exception(override val message: String?): hamusutax.ktor.clientapp.alist.utils.AlistCodeException(403, message)
class Alist500Exception(override val message: String?): hamusutax.ktor.clientapp.alist.utils.AlistCodeException(500, message)
