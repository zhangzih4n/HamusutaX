package hamusutax.formats.clashconfig.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import hamusutax.formats.clashconfig.model.ClashConfig.FindProcessMode.STRICT
import hamusutax.formats.clashconfig.model.ClashConfig.LogLevel.INFO
import hamusutax.formats.clashconfig.model.ClashConfig.Mode.RULE
import hamusutax.formats.clashconfig.model.ClashConfig.Proxy.Type.DIRECT

@Serializable
data class ClashConfig(
    @SerialName("allow-lan") val allowLan: Boolean? = null,
    @SerialName("bind-address") val bindAddress: String = "*",
    @SerialName("lan-allowed-ips") val lanAllowedIps: List<String>? = null,
    @SerialName("lan-disallowed-ips") val lanDisallowedIps: List<String>? = null,
    val authentication: List<String>? = null,
    @SerialName("skip-auth-prefixes") val skipAuthPrefixes: List<String>? = null,
    val mode: Mode = RULE,
    @SerialName("log-level") val logLevel: LogLevel = INFO,
    val ipv6: Boolean? = null,
    @SerialName("keep-alive-interval") val keepAliveInterval: Int? = null,
    @SerialName("find-process-mode") val findProcessMode: FindProcessMode = STRICT,
    @SerialName("external-controller") val externalController: String? = null,
    @SerialName("external-controller-unix") val externalControllerUnix: String? = null,
    val secret: String? = null,
    @SerialName("external-ui") val externalUi: String? = null,
    @SerialName("external-ui-name") val externalUiName: String? = null,
    @SerialName("external-ui-url") val externalUiUrl: String? = null,
    // TODO: profile
    @SerialName("unified-delay") val unifiedDelay: Boolean? = null,
    @SerialName("tcp-concurrent") val tcpConcurrent: Boolean? = null,
    @SerialName("interface-name") val interfaceName: String? = null,
    @SerialName("routing-mark") val routingMark: String? = null,
    // TODO: tls
    @SerialName("global-client-fingerprint") val globalClientFingerprint: String? = null,
    @SerialName("geodata-mode") val geodataMode: Boolean? = null,
    @SerialName("geodata-loader") val geodataLoader: String? = null,
    @SerialName("geo-auto-update") val geoAutoUpdate: Boolean? = null,
    @SerialName("geo-update-interval") val geoUpdateInterval: Int? = null,
    // TODO: geox-url
    @SerialName("global-ua") val globalUa: String? = null,
    val dns: Dns? = null,
    val port: Int? = null,
    @SerialName("socks-port") val socksPort: Int? = null,
    @SerialName("mixed-port") val mixedPort: Int? = null,
    @SerialName("redir-port") val redirPort: Int? = null,
    @SerialName("tproxy-port") val tproxyPort: Int? = null,
    val tun: Tun? = null,
    // TODO: listeners
    val proxies: List<Proxy>? = null
) {
    @Serializable
    enum class Mode {
        @SerialName("rule") RULE,
        @SerialName("global") GLOBAL,
        @SerialName("direct") DIRECT
    }

    @Serializable
    enum class LogLevel {
        @SerialName("silent") SILENT,
        @SerialName("error") ERROR,
        @SerialName("warning") WARNING,
        @SerialName("info") INFO,
        @SerialName("debug") DEBUG
    }

    @Serializable
    enum class FindProcessMode {
        @SerialName("always") ALWAYS,
        @SerialName("strict") STRICT,
        @SerialName("off") OFF
    }

    @Serializable
    data class Dns(
        val enable: Boolean,
        @SerialName("prefer-h3") val preferH3: Boolean,
        @SerialName("use-hosts") val useHosts: Boolean,
        @SerialName("use-system-hosts") val useSystemHosts: Boolean,
        val listen: String,
        val ipv6: Boolean,
        @SerialName("default-nameserver") val defaultNameserver: List<String>,
        @SerialName("enhanced-mode") val enhancedMode: String,
        @SerialName("fake-ip-range") val fakeIpRange: List<String>,
        @SerialName("fake-ip-filter") val fakeIpFilter: List<String>,
        @SerialName("nameserver-policy") val nameserverPolicy: Map<String, String>,
        val nameserver: List<String>,
        val fallback: List<String>,
        @SerialName("proxy-server-nameserver") val proxyServerNameserver: List<String>,
        @SerialName("fallback-filter") val fallbackFilter: FallbackFilter
    ) {
        @Serializable
        data class FallbackFilter(
            val geoip: Boolean,
            @SerialName("geoip-code") val geoipCode: String,
            val geosite: List<String>,
            val ipcidr: List<String>,
            val domain: List<String>
        )
    }

    @Serializable
    data class Tun(
        val enable: Boolean,
        val stack: Stack,
        @SerialName("auto-route") val autoRoute: Boolean,
        @SerialName("auto-detect-interface") val autoDetectInterface: Boolean,
        @SerialName("dns-hijack") val dnsHijack: List<String>,
        val device: String,
        val mtu: Int,
        @SerialName("strict-route") val strictRoute: Boolean,
        val gso: Boolean,
        @SerialName("gso-max-size") val gsoMaxSize: Int,
        @SerialName("udp-timeout") val udpTimeout: Int,
        @SerialName("endpoint-independent-nat") val endpointIndependentNat: Boolean,
        @SerialName("include-interface") val includeInterface: List<String>,
        @SerialName("exclude-interface") val excludeInterface: List<String>,
        @SerialName("inet4-route-address") val inet4RouteAddress: List<String>,
        @SerialName("inet6-route-address") val inet6RouteAddress: List<String>,
        @SerialName("inet4-route-exclude-address") val inet4RouteExcludeAddress: List<String>,
        @SerialName("inet6-route-exclude-address") val inet6RouteExcludeAddress: List<String>,
        @SerialName("include-uid") val includeUid: String,
        @SerialName("include-uid-range") val includeUidRange: String,
        @SerialName("exclude-uid") val excludeUid: String,
        @SerialName("exclude-uid-range") val excludeUidRange: String,
        @SerialName("include-android-user") val includeAndroidUser: String,
        @SerialName("include-package") val includePackage: String,
        @SerialName("exclude-package") val excludePackage: String,
        @SerialName("table-index") val tableIndex: String
    ) {
        @Serializable
        enum class Stack {
            @SerialName("system") SYSTEM,
            @SerialName("gvisor") GVISOR,
            @SerialName("mixed") MIXED
        }
    }

    interface Proxy {
        val name: String
        val type: Type
        val server: String?
        val port: Int?
        @SerialName("ip-version") val ipVersion: String?
        val udp: Boolean?
        @SerialName("interface-name") val interfaceName: String?
        @SerialName("routing-mark") val routingMark: String?
        val tfo: Boolean?
        val mptcp: Boolean?
        @SerialName("dialer-proxy") val dialerProxy: String?

        @Serializable
        enum class Type {
            @SerialName("direct") DIRECT,
            @SerialName("dns") DNS,
            @SerialName("http") HTTP,
            @SerialName("socks5") SOCKS5,
            @SerialName("ss") SHADOWSOCKS,
            @SerialName("ssr") SHADOWSOCKSR,
            @SerialName("vmess") VMESS,
            @SerialName("vless") VLESS,
            @SerialName("trojan") TROJAN,
            @SerialName("hysteria") HYSTERIA,
            @SerialName("hysteria2") HYSTERIA2,
            @SerialName("tuic") TUIC,
            @SerialName("wg") WIREGUARD,
            @SerialName("ssh") SSH
        }

        @Serializable
        data class Direct(
            override val name: String,
            override val ipVersion: String? = null,
            override val udp: Boolean? = null,
            override val interfaceName: String? = null,
            override val routingMark: String? = null,
            override val tfo: Boolean? = null,
            override val mptcp: Boolean? = null,
            override val dialerProxy: String? = null
        ) : Proxy {
            override val type = DIRECT
            override val server = null
            override val port = null
        }
    }
    // TODO: tls, transport-layer

}
