package hamusutax.clients.alist.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Auth2faGenerateResponse(
    /**
     * 二维码图片的 Data URL
     */
    val qr: String,
    val secret: String
)
