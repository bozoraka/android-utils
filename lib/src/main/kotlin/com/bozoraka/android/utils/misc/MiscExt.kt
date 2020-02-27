package com.bozoraka.android.utils.misc

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass

fun Boolean?.falseOnNull(): Boolean = this == true

fun BigInteger?.orZero(): BigInteger = this ?: BigInteger.ZERO

fun BigDecimal?.orZero(): BigDecimal = this ?: BigDecimal.ZERO

fun Int?.orZero(): Int = this ?: 0

fun Long?.orZero(): Long = this ?: 0L

fun collectionTypeOf(kClass: KClass<out Any>): Type =
    TypeToken.getParameterized(List::class.java, kClass.java).type
