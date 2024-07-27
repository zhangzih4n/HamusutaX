@file:Suppress("unused")
package hamusutax.android.sharedpreferences

import android.content.SharedPreferences
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import java.io.IOException

fun SharedPreferences.getString(key: String) =
    (if (contains(key)) getString(key, "") else null)
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getStringOrNull(key: String) =
    if (contains(key)) getString(key, "") else null

@RequiresApi(VERSION_CODES.HONEYCOMB)
fun SharedPreferences.getStringSet(key: String): Set<String> =
    (if (contains(key)) getStringSet(key, emptySet()) else null)
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

@RequiresApi(VERSION_CODES.HONEYCOMB)
fun SharedPreferences.getStringSetOrNull(key: String): Set<String>? =
    if (contains(key)) getStringSet(key, emptySet()) else null

fun SharedPreferences.getInt(key: String) =
    if (contains(key)) getInt(key, 0) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getIntOrNull(key: String) =
    if (contains(key)) getInt(key, 0) else null

fun SharedPreferences.getLong(key: String) =
    if (contains(key)) getLong(key, 0) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getLongOrNull(key: String) =
    if (contains(key)) getLong(key, 0) else null

fun SharedPreferences.getFloat(key: String) =
    if (contains(key)) getFloat(key, 0F) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getFloatOrNull(key: String) =
    if (contains(key)) getFloat(key, 0F) else null

fun SharedPreferences.getDouble(key: String) =
    if (contains(key)) Double.fromBits(getLong(key, 0)) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getDoubleOrNull(key: String) =
    if (contains(key)) Double.fromBits(getLong(key, 0)) else null

fun SharedPreferences.getBoolean(key: String) =
    if (contains(key)) getBoolean(key, false) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

fun SharedPreferences.getBooleanOrNull(key: String) =
    if (contains(key)) getBoolean(key, false) else null

inline fun <reified T: Enum<T>> SharedPreferences.getEnum(key: String): T =
    if (contains(key)) enumValueOf(getString(key = key)) else null
        ?: throw IOException("No value with key \"$key\" in SharedPreferences!")

inline fun <reified T: Enum<T>> SharedPreferences.getEnumOrNull(key: String): T? =
    if (contains(key)) enumValueOf<T>(getString(key = key)) else null

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun SharedPreferences.putString(key: String, value: String) =
    edit().putString(key, value).apply()

@RequiresApi(VERSION_CODES.HONEYCOMB)
fun SharedPreferences.putStringSet(key: String, value: Set<String>) =
    edit().putStringSet(key, value).apply()

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun SharedPreferences.putBoolean(key: String, value: Boolean) =
    edit().putBoolean(key, value).apply()

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun SharedPreferences.putInt(key: String, value: Int) =
    edit().putInt(key, value).apply()

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun SharedPreferences.putLong(key: String, value: Long) =
    edit().putLong(key, value).apply()

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun SharedPreferences.putFloat(key: String, value: Float) =
    edit().putFloat(key, value).apply()

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun SharedPreferences.putDouble(key: String, value: Double) =
    edit().putLong(key, value.toRawBits()).apply()

@RequiresApi(VERSION_CODES.GINGERBREAD)
fun SharedPreferences.clear() =
    edit().clear().apply()
