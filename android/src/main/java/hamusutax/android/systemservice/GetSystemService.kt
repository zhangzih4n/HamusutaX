@file:Suppress("UNUSED")
package hamusutax.android.systemservice

import android.accounts.AccountManager
import android.app.ActivityManager
import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.DownloadManager
import android.app.LocaleManager
import android.app.NotificationManager
import android.app.admin.DevicePolicyManager
import android.app.appsearch.AppSearchManager
import android.app.blob.BlobStoreManager
import android.appwidget.AppWidgetManager
import android.bluetooth.BluetoothManager
import android.companion.CompanionDeviceManager
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.CrossProfileApps
import android.credentials.CredentialManager
import android.devicelock.DeviceLockManager
import android.hardware.ConsumerIrManager
import android.hardware.biometrics.BiometricManager
import android.hardware.camera2.CameraManager
import android.hardware.display.DisplayManager
import android.hardware.usb.UsbManager
import android.location.LocationManager
import android.media.AudioManager
import android.net.ConnectivityDiagnosticsManager
import android.net.ConnectivityManager
import android.net.VpnManager
import android.net.wifi.WifiManager
import android.net.wifi.aware.WifiAwareManager
import android.net.wifi.p2p.WifiP2pManager
import android.net.wifi.rtt.WifiRttManager
import android.nfc.NfcManager
import android.os.BatteryManager
import android.os.BugreportManager
import android.os.Build.VERSION_CODES
import android.os.DropBoxManager
import android.os.PowerManager
import android.os.VibratorManager
import android.os.health.SystemHealthManager
import android.os.storage.StorageManager
import android.security.FileIntegrityManager
import android.telephony.CarrierConfigManager
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.CaptioningManager
import android.view.displayhash.DisplayHashManager
import android.view.textservice.TextServicesManager
import androidx.annotation.RequiresApi
import androidx.core.app.LocaleManagerCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

fun Context.getNotificationManager() =
    ContextCompat.getSystemService(this, NotificationManager::class.java)

fun Context.getNotificationManagerCompat() =
    NotificationManagerCompat.from(this)

fun Context.getLocationManager() =
    ContextCompat.getSystemService(this, LocationManager::class.java)

fun Context.getDropBoxManager() =
    ContextCompat.getSystemService(this, DropBoxManager::class.java)

fun Context.getDownloadManager() =
    ContextCompat.getSystemService(this, DownloadManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getDisplayHashManager() =
    ContextCompat.getSystemService(this, DisplayHashManager::class.java)

fun Context.getDisplayManager() =
    ContextCompat.getSystemService(this, DisplayManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getCredentialManager() =
    ContextCompat.getSystemService(this, CredentialManager::class.java)

fun Context.getCarrierConfigManager() =
    ContextCompat.getSystemService(this, CarrierConfigManager::class.java)

fun Context.getCrossProfileApps() =
    ContextCompat.getSystemService(this, CrossProfileApps::class.java)

fun Context.getConsumerIrManager() =
    ContextCompat.getSystemService(this, ConsumerIrManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getFileIntegrityManager() =
    ContextCompat.getSystemService(this, FileIntegrityManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getConnectivityDiagnosticsManager() =
    ContextCompat.getSystemService(this, ConnectivityDiagnosticsManager::class.java)

fun Context.getCompanionDeviceManager() =
    ContextCompat.getSystemService(this, CompanionDeviceManager::class.java)

fun Context.getClipboardManager() =
    ContextCompat.getSystemService(this, ClipboardManager::class.java)

fun Context.getStorageManager() =
    ContextCompat.getSystemService(this, StorageManager::class.java)

fun Context.getUsbManager() =
    ContextCompat.getSystemService(this, UsbManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getBugreportManager() =
    ContextCompat.getSystemService(this, BugreportManager::class.java)

fun Context.getCameraManager() =
    ContextCompat.getSystemService(this, CameraManager::class.java)

fun Context.getSystemHealthManager() =
    ContextCompat.getSystemService(this, SystemHealthManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getBlobStoreManager() =
    ContextCompat.getSystemService(this, BlobStoreManager::class.java)

fun Context.getAudioManager() =
    ContextCompat.getSystemService(this, AudioManager::class.java)

fun Context.getTextServicesManager() =
    ContextCompat.getSystemService(this, TextServicesManager::class.java)


fun Context.getAppOpsManager() =
    ContextCompat.getSystemService(this, AppOpsManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getAppSearchManager() =
    ContextCompat.getSystemService(this, AppSearchManager::class.java)

@RequiresApi(VERSION_CODES.S)
fun Context.getVibratorManager() =
    ContextCompat.getSystemService(this, VibratorManager::class.java)

fun Context.getAppWidgetManager() =
    ContextCompat.getSystemService(this, AppWidgetManager::class.java)

fun Context.getDevicePolicyManager() =
    ContextCompat.getSystemService(this, DevicePolicyManager::class.java)

@RequiresApi(VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.getDeviceLockManager() =
    ContextCompat.getSystemService(this, DeviceLockManager::class.java)

@RequiresApi(VERSION_CODES.Q)
fun Context.getBiometricManager() =
    ContextCompat.getSystemService(this, BiometricManager::class.java)

fun Context.getNfcManager() =
    ContextCompat.getSystemService(this, NfcManager::class.java)

@RequiresApi(VERSION_CODES.R)
fun Context.getVpnManager() =
    ContextCompat.getSystemService(this, VpnManager::class.java)

fun Context.getConnectivityManager() =
    ContextCompat.getSystemService(this, ConnectivityManager::class.java)

fun Context.getWifiManager() =
    ContextCompat.getSystemService(this, WifiManager::class.java)

fun Context.getWifiAwareManager() =
    ContextCompat.getSystemService(this, WifiAwareManager::class.java)

fun Context.getWifiP2pManager() =
    ContextCompat.getSystemService(this, WifiP2pManager::class.java)

fun Context.getWifiRttManager() =
    ContextCompat.getSystemService(this, WifiRttManager::class.java)

fun Context.getBluetoothManager() =
    ContextCompat.getSystemService(this, BluetoothManager::class.java)

fun Context.getPowerManager() =
    ContextCompat.getSystemService(this, PowerManager::class.java)

fun Context.getBatteryManager() =
    ContextCompat.getSystemService(this, BatteryManager::class.java)

fun Context.getWindowManager() =
    ContextCompat.getSystemService(this, WindowManager::class.java)

fun Context.getAccountManager() =
    ContextCompat.getSystemService(this, AccountManager::class.java)

fun Context.getActivityManager() =
    ContextCompat.getSystemService(this, ActivityManager::class.java)

fun Context.getAlarmManager() =
    ContextCompat.getSystemService(this, AlarmManager::class.java)

fun Context.getAccessibilityManager() =
    ContextCompat.getSystemService(this, AccessibilityManager::class.java)

fun Context.getCaptioningManager() =
    ContextCompat.getSystemService(this, CaptioningManager::class.java)

@RequiresApi(VERSION_CODES.TIRAMISU)
fun Context.getLocaleManager() =
    ContextCompat.getSystemService(this, LocaleManager::class.java)

fun Context.getSystemLocales() =
    LocaleManagerCompat.getSystemLocales(this)

fun Context.getApplicationLocales() =
    LocaleManagerCompat.getApplicationLocales(this)
