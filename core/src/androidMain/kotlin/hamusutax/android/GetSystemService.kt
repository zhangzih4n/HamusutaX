@file:Suppress("unused", "ObsoleteSdkInt")
package hamusutax.android

import android.accounts.AccountManager
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.DownloadManager
import android.app.GameManager
import android.app.GrammaticalInflectionManager
import android.app.KeyguardManager
import android.app.LocaleManager
import android.app.NotificationManager
import android.app.StatusBarManager
import android.app.UiModeManager
import android.app.WallpaperManager
import android.app.admin.DevicePolicyManager
import android.app.appsearch.AppSearchManager
import android.app.blob.BlobStoreManager
import android.app.people.PeopleManager
import android.app.role.RoleManager
import android.app.slice.SliceManager
import android.app.usage.NetworkStatsManager
import android.app.usage.StorageStatsManager
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.companion.virtual.VirtualDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.om.OverlayManager
import android.content.pm.CrossProfileApps
import android.content.pm.ShortcutManager
import android.content.pm.verify.domain.DomainVerificationManager
import android.content.res.AssetManager
import android.credentials.CredentialManager
import android.devicelock.DeviceLockManager
import android.hardware.ConsumerIrManager
import android.hardware.SensorManager
import android.hardware.SensorPrivacyManager
import android.hardware.biometrics.BiometricManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.fingerprint.FingerprintManager
import android.hardware.input.InputManager
import android.hardware.lights.LightsManager
import android.hardware.usb.UsbManager
import android.health.connect.HealthConnectManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaCommunicationManager
import android.media.metrics.MediaMetricsManager
import android.media.midi.MidiManager
import android.media.projection.MediaProjectionManager
import android.media.session.MediaSessionManager
import android.media.tv.TvInputManager
import android.media.tv.interactive.TvInteractiveAppManager
import android.net.ConnectivityDiagnosticsManager
import android.net.ConnectivityManager
import android.net.IpSecManager
import android.net.VpnManager
import android.net.nsd.NsdManager
import android.net.sip.SipManager
import android.net.vcn.VcnManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.BatteryManager
import android.os.BugreportManager
import android.os.Build.VERSION_CODES
import android.os.DropBoxManager
import android.os.HardwarePropertiesManager
import android.os.PerformanceHintManager
import android.os.PowerManager
import android.os.UserManager
import android.os.VibratorManager
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.print.PrintManager
import android.security.FileIntegrityManager
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SmsManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.telephony.euicc.EuiccManager
import android.telephony.ims.ImsManager
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.autofill.AutofillManager
import android.view.contentcapture.ContentCaptureManager
import android.view.displayhash.DisplayHashManager
import android.view.inputmethod.InputMethodManager
import android.view.textclassifier.TextClassificationManager
import android.view.textservice.TextServicesManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import javax.net.ssl.TrustManager

fun Context.getActivityManager() =
    ContextCompat.getSystemService(this, ActivityManager::class.java)

fun Context.getAlarmManager() =
    ContextCompat.getSystemService(this, AlarmManager::class.java)

fun Context.getAssetManager() =
    ContextCompat.getSystemService(this, AssetManager::class.java)

fun Context.getAudioManager() =
    ContextCompat.getSystemService(this, AudioManager::class.java)

fun Context.getBatteryManager() =
    ContextCompat.getSystemService(this, BatteryManager::class.java)

fun Context.getConnectivityManager() =
    ContextCompat.getSystemService(this, ConnectivityManager::class.java)

fun Context.getKeyguardManager() =
    ContextCompat.getSystemService(this, KeyguardManager::class.java)

fun Context.getLocationManager() =
    ContextCompat.getSystemService(this, LocationManager::class.java)

fun Context.getNotificationManager() =
    ContextCompat.getSystemService(this, NotificationManager::class.java)

fun Context.getPowerManager() =
    ContextCompat.getSystemService(this, PowerManager::class.java)

fun Context.getSensorManager() =
    ContextCompat.getSystemService(this, SensorManager::class.java)

fun Context.getTelephonyManager() =
    ContextCompat.getSystemService(this, TelephonyManager::class.java)

fun Context.getTrustManager() =
    ContextCompat.getSystemService(this, TrustManager::class.java)

fun Context.getWifiManager() =
    ContextCompat.getSystemService(this, WifiManager::class.java)

fun Context.getWindowManager() =
    ContextCompat.getSystemService(this, WindowManager::class.java)

@RequiresApi(VERSION_CODES.CUPCAKE)
fun Context.getAppWidgetManager() =
    ContextCompat.getSystemService(this, AppWidgetManager::class.java)

@RequiresApi(VERSION_CODES.CUPCAKE)
fun Context.getInputMethodManager() =
    ContextCompat.getSystemService(this, InputMethodManager::class.java)

@RequiresApi(VERSION_CODES.DONUT)
fun Context.getAccessibilityManager() =
    ContextCompat.getSystemService(this, AccessibilityManager::class.java)

@RequiresApi(VERSION_CODES.DONUT)
fun Context.getSmsManager() =
    ContextCompat.getSystemService(this, SmsManager::class.java)

@RequiresApi(VERSION_CODES.ECLAIR)
fun Context.getAccountManager() =
    ContextCompat.getSystemService(this, AccountManager::class.java)

@RequiresApi(VERSION_CODES.ECLAIR)
fun Context.getWallpaperManager() =
    ContextCompat.getSystemService(this, WallpaperManager::class.java)

@RequiresApi(VERSION_CODES.FROYO)
fun Context.getDevicePolicyManager() =
    ContextCompat.getSystemService(this, DevicePolicyManager::class.java)

@RequiresApi(VERSION_CODES.FROYO)
fun Context.getDropBoxManager() =
    ContextCompat.getSystemService(this, DropBoxManager::class.java)

@RequiresApi(VERSION_CODES.FROYO)
fun Context.getUiModeManager() =
    ContextCompat.getSystemService(this, UiModeManager::class.java)

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun Context.getDownloadManager() =
    ContextCompat.getSystemService(this, DownloadManager::class.java)

@Deprecated("For more information about using SIP, read the Session Initiation Protocol developer guide.")
@RequiresApi(VERSION_CODES.GINGERBREAD)
fun Context.getSipManager() =
    ContextCompat.getSystemService(this, SipManager::class.java)

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun Context.getStorageManager() =
    ContextCompat.getSystemService(this, StorageManager::class.java)

@RequiresApi(VERSION_CODES.GINGERBREAD_MR1)
fun Context.getNfcManager() =
    ContextCompat.getSystemService(this, NfcManager::class.java)

@RequiresApi(VERSION_CODES.HONEYCOMB)
fun Context.getClipboardManager() =
    ContextCompat.getSystemService(this, ClipboardManager::class.java)

@RequiresApi(VERSION_CODES.HONEYCOMB_MR1)
fun Context.getUsbManager() =
    ContextCompat.getSystemService(this, UsbManager::class.java)

@RequiresApi(VERSION_CODES.ICE_CREAM_SANDWICH)
fun Context.getTextServicesManager() =
    ContextCompat.getSystemService(this, TextServicesManager::class.java)

@RequiresApi(VERSION_CODES.ICE_CREAM_SANDWICH)
fun Context.getWifiP2pManager() =
    ContextCompat.getSystemService(this, WifiP2pManager::class.java)

@RequiresApi(VERSION_CODES.JELLY_BEAN)
fun Context.getInputManager() =
    ContextCompat.getSystemService(this, InputManager::class.java)

@RequiresApi(VERSION_CODES.JELLY_BEAN)
fun Context.getNsdManager() =
    ContextCompat.getSystemService(this, NsdManager::class.java)

@RequiresApi(VERSION_CODES.JELLY_BEAN_MR1)
fun Context.getDisplayManager() =
    ContextCompat.getSystemService(this, DisplayManager::class.java)

@RequiresApi(VERSION_CODES.JELLY_BEAN_MR1)
fun Context.getUserManager() =
    ContextCompat.getSystemService(this, UserManager::class.java)

@RequiresApi(VERSION_CODES.JELLY_BEAN_MR2)
fun Context.getBluetoothManager() =
    ContextCompat.getSystemService(this, BluetoothManager::class.java)

@RequiresApi(VERSION_CODES.KITKAT)
fun Context.getAppOpsManager() =
    ContextCompat.getSystemService(this, AppOpsManager::class.java)

@RequiresApi(VERSION_CODES.KITKAT)
fun Context.getCaptioningManager() =
    ContextCompat.getSystemService(this, CaptioningManager::class.java)

@RequiresApi(VERSION_CODES.KITKAT)
fun Context.getConsumerIrManager() =
    ContextCompat.getSystemService(this, ConsumerIrManager::class.java)

@RequiresApi(VERSION_CODES.KITKAT)
fun Context.getPrintManager() =
    ContextCompat.getSystemService(this, PrintManager::class.java)

@RequiresApi(VERSION_CODES.LOLLIPOP)
fun Context.getCameraManager() =
    ContextCompat.getSystemService(this, CameraManager::class.java)

@RequiresApi(VERSION_CODES.LOLLIPOP)
fun Context.getMediaProjectionManager() =
    ContextCompat.getSystemService(this, MediaProjectionManager::class.java)

@RequiresApi(VERSION_CODES.LOLLIPOP)
fun Context.getMediaSessionManager() =
    ContextCompat.getSystemService(this, MediaSessionManager::class.java)

@RequiresApi(VERSION_CODES.LOLLIPOP)
fun Context.getTelecomManager() =
    ContextCompat.getSystemService(this, TelecomManager::class.java)

@RequiresApi(VERSION_CODES.LOLLIPOP)
fun Context.getTvInputManager() =
    ContextCompat.getSystemService(this, TvInputManager::class.java)

@RequiresApi(VERSION_CODES.LOLLIPOP)
fun Context.getUsageStatsManager() =
    ContextCompat.getSystemService(this, UsageStatsManager::class.java)

@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
fun Context.getSubscriptionManager() =
    ContextCompat.getSystemService(this, SubscriptionManager::class.java)

@RequiresApi(VERSION_CODES.M)
fun Context.getCarrierConfigManager() =
    ContextCompat.getSystemService(this, CarrierConfigManager::class.java)

@Deprecated("See BiometricPrompt which shows a system-provided dialog upon starting authentication. In a world where devices may have different types of biometric authentication, it's much more realistic to have a system-provided authentication dialog since the method may vary by vendor/device.")
@RequiresApi(VERSION_CODES.M)
fun Context.getFingerprintManager() =
    ContextCompat.getSystemService(this, FingerprintManager::class.java)

@RequiresApi(VERSION_CODES.M)
fun Context.getMidiManager() =
    ContextCompat.getSystemService(this, MidiManager::class.java)

@RequiresApi(VERSION_CODES.M)
fun Context.getNetworkStatsManager() =
    ContextCompat.getSystemService(this, NetworkStatsManager::class.java)

@RequiresApi(VERSION_CODES.N)
fun Context.getHardwarePropertiesManager() =
    ContextCompat.getSystemService(this, HardwarePropertiesManager::class.java)

@RequiresApi(VERSION_CODES.N)
fun Context.getSystemHealthManager() =
    ContextCompat.getSystemService(this, SystemHealthManager::class.java)

@RequiresApi(VERSION_CODES.N_MR1)
fun Context.getShortcutManager() =
    ContextCompat.getSystemService(this, ShortcutManager::class.java)

@RequiresApi(VERSION_CODES.O)
fun Context.getAutofillManager() =
    ContextCompat.getSystemService(this, AutofillManager::class.java)

@RequiresApi(VERSION_CODES.O)
fun Context.getCompanionDeviceManager() =
    ContextCompat.getSystemService(this, CompanionDeviceManager::class.java)

@RequiresApi(VERSION_CODES.O)
fun Context.getStorageStatsManager() =
    ContextCompat.getSystemService(this, StorageStatsManager::class.java)

@RequiresApi(VERSION_CODES.O)
fun Context.getTextClassificationManager() =
    ContextCompat.getSystemService(this, TextClassificationManager::class.java)

@RequiresApi(VERSION_CODES.O)
fun Context.getWifiAwareManager() =
    ContextCompat.getSystemService(this, WifiAwareManager::class.java)

@RequiresApi(VERSION_CODES.P)
fun Context.getCrossProfileApps() =
    ContextCompat.getSystemService(this, CrossProfileApps::class.java)

@RequiresApi(VERSION_CODES.P)
fun Context.getEuiccManager() =
    ContextCompat.getSystemService(this, EuiccManager::class.java)

@RequiresApi(VERSION_CODES.P)
fun Context.getIpSecManager() =
    ContextCompat.getSystemService(this, IpSecManager::class.java)

@RequiresApi(VERSION_CODES.P)
fun Context.getSliceManager() =
    ContextCompat.getSystemService(this, SliceManager::class.java)

@RequiresApi(VERSION_CODES.P)
fun Context.getWifiRttManager() =
    ContextCompat.getSystemService(this, WifiRttManager::class.java)

@RequiresApi(VERSION_CODES.Q)
fun Context.getBiometricManager() =
    ContextCompat.getSystemService(this, BiometricManager::class.java)

@RequiresApi(VERSION_CODES.Q)
fun Context.getContentCaptureManager() =
    ContextCompat.getSystemService(this, ContentCaptureManager::class.java)

@RequiresApi(VERSION_CODES.Q)
fun Context.getRoleManager() =
    ContextCompat.getSystemService(this, RoleManager::class.java)

@RequiresApi(VERSION_CODES.Q)
fun Context.getStatusBarManager() =
    ContextCompat.getSystemService(this, StatusBarManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getBlobStoreManager() =
    ContextCompat.getSystemService(this, BlobStoreManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getConnectivityDiagnosticsManager() =
    ContextCompat.getSystemService(this, ConnectivityDiagnosticsManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getFileIntegrityManager() =
    ContextCompat.getSystemService(this, FileIntegrityManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getImsManager() =
    ContextCompat.getSystemService(this, ImsManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getVpnManager() =
    ContextCompat.getSystemService(this, VpnManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getAppSearchManager() =
    ContextCompat.getSystemService(this, AppSearchManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getBugreportManager() =
    ContextCompat.getSystemService(this, BugreportManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getDisplayHashManager() =
    ContextCompat.getSystemService(this, DisplayHashManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getDomainVerificationManager() =
    ContextCompat.getSystemService(this, DomainVerificationManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getGameManager() =
    ContextCompat.getSystemService(this, GameManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getLightsManager() =
    ContextCompat.getSystemService(this, LightsManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getMediaCommunicationManager() =
    ContextCompat.getSystemService(this, MediaCommunicationManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getMediaMetricsManager() =
    ContextCompat.getSystemService(this, MediaMetricsManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getPeopleManager() =
    ContextCompat.getSystemService(this, PeopleManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getPerformanceHintManager() =
    ContextCompat.getSystemService(this, PerformanceHintManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getSensorPrivacyManager() =
    ContextCompat.getSystemService(this, SensorPrivacyManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getVcnManager() =
    ContextCompat.getSystemService(this, VcnManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getVibratorManager() =
    ContextCompat.getSystemService(this, VibratorManager::class.java)

@RequiresApi(VERSION_CODES.TIRAMISU)
fun Context.getLocaleManager() =
    ContextCompat.getSystemService(this, LocaleManager::class.java)

@RequiresApi(VERSION_CODES.TIRAMISU)
fun Context.getTvInteractiveAppManager() =
    ContextCompat.getSystemService(this, TvInteractiveAppManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getCredentialManager() =
    ContextCompat.getSystemService(this, CredentialManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getDeviceLockManager() =
    ContextCompat.getSystemService(this, DeviceLockManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getGrammaticalInflectionManager() =
    ContextCompat.getSystemService(this, GrammaticalInflectionManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getHealthConnectManager() =
    ContextCompat.getSystemService(this, HealthConnectManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getOverlayManager() =
    ContextCompat.getSystemService(this, OverlayManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getVirtualDeviceManager() =
    ContextCompat.getSystemService(this, VirtualDeviceManager::class.java)
