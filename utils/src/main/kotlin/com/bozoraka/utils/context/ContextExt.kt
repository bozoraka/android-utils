package com.bozoraka.utils.context

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

fun Context.openActivity(
        activityClass: KClass<out Activity>,
        action: ((intent: Intent) -> Unit)? = null
) {
    val intent = Intent(this, activityClass.java)
    action?.invoke(intent)
    startActivity(intent)
}

fun Activity.openActivityForResult(
        activityClass: KClass<out Activity>,
        requestCode: Int,
        action: ((intent: Intent) -> Unit)? = null
) {
    val intent = Intent(this, activityClass.java)
    action?.invoke(intent)
    startActivityForResult(intent, requestCode)
}

fun Fragment.openActivity(
        activityClass: KClass<out Activity>,
        action: ((intent: Intent) -> Unit)? = null
) {
    context ?: return

    val intent = Intent(context, activityClass.java)
    action?.invoke(intent)
    startActivity(intent)
}

fun Fragment.openActivityForResult(
        activityClass: KClass<out Activity>,
        requestCode: Int,
        action: ((intent: Intent) -> Unit)? = null
) {
    val intent = Intent(context, activityClass.java)
    action?.invoke(intent)
    startActivityForResult(intent, requestCode)
}

fun Context.hasPermission(permission: String): Boolean {
    return try {
        PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, permission)
    } catch (e: Throwable) {
        false
    }
}

fun Activity.requestPermission(permission: String, requestCode: Int) {
    ActivityCompat.requestPermissions(
            this,
            arrayOf(permission), requestCode
    )
}
