package com.bozoraka.android.utils.preferences

import android.content.SharedPreferences
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KProperty

fun SharedPreferences.boolean(key: String, defValue: Boolean = false) =
    BoolItem(this, key, defValue)

fun SharedPreferences.int(key: String, defValue: Int = 0) =
    IntItem(this, key, defValue)

fun SharedPreferences.bigInteger(key: String, defValue: BigInteger = BigInteger.ZERO) =
    BigIntegerItem(this, key, defValue)

fun SharedPreferences.bigDecimal(key: String, defValue: BigDecimal = BigDecimal.ZERO) =
    BigDecimalItem(this, key, defValue)

fun SharedPreferences.long(key: String, defValue: Long = 0) =
    LongItem(this, key, defValue)

fun SharedPreferences.string(key: String, defValue: String = "") =
    StringItem(this, key, defValue)

fun SharedPreferences.stringNullable(key: String, defValue: String? = null) =
    StringItemNullable(this, key, defValue)

class BoolItem(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: Boolean
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }
}

class IntItem(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: Int
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return preferences.getInt(key, defValue)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }
}

class LongItem(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: Long
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return preferences.getLong(key, defValue)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }
}

class BigIntegerItem(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: BigInteger
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): BigInteger {
        return preferences.getString(key, null)?.toBigIntegerOrNull() ?: defValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: BigInteger) {
        preferences.edit().putString(key, value.toString()).apply()
    }
}

class BigDecimalItem(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: BigDecimal
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): BigDecimal {
        return preferences.getString(key, null)?.toBigDecimalOrNull() ?: defValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: BigDecimal) {
        preferences.edit().putString(key, value.toString()).apply()
    }
}

class StringItem(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: String
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return preferences.getString(key, defValue).orEmpty()
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        preferences.edit().putString(key, value).apply()
    }
}

class StringItemNullable(
    private val preferences: SharedPreferences,
    private val key: String,
    private val defValue: String?
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return preferences.getString(key, defValue)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        preferences.edit().putString(key, value).apply()
    }
}
