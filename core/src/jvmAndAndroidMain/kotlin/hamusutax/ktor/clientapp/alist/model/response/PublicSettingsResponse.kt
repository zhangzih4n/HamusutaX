package hamusutax.ktor.clientapp.alist.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublicSettingsResponse(
    @SerialName("allow_indexed") val allowIndexed: String,
    @SerialName("allow_mounted") val allowMounted: String,
    val announcement: String,
    @SerialName("audio_autoplay") val audioAutoplay: String,
    @SerialName("audio_cover") val audioCover: String,
    @SerialName("auto_update_index") val autoUpdateIndex: String,
    @SerialName("default_page_size") val defaultPageSize: String,
    @SerialName("external_previews") val externalPreviews: String,
    val favicon: String,
    @SerialName("filename_char_mapping") val filenameCharMapping: String,
    @SerialName("forward_direct_link_params") val forwardDirectLinkParams: String,
    @SerialName("hide_files") val hideFiles: String,
    @SerialName("home_container") val homeContainer: String,
    @SerialName("home_icon") val homeIcon: String,
    @SerialName("iframe_previews") val iframePreviews: String,
    val logo: String,
    @SerialName("main_color") val mainColor: String,
    @SerialName("ocr_api") val ocrApi: String,
    @SerialName("package_download") val packageDownload: String,
    @SerialName("pagination_type") val paginationType: String,
    @SerialName("robots_txt") val robotsTxt: String,
    @SerialName("search_index") val searchIndex: String,
    @SerialName("settings_layout") val settingsLayout: String,
    @SerialName("site_title") val siteTitle: String,
    @SerialName("sso_login_enabled") val ssoLoginEnabled: String,
    @SerialName("sso_login_platform") val ssoLoginPlatform: String,
    val version: String,
    @SerialName("video_autoplay") val videoAutoplay: String
)
