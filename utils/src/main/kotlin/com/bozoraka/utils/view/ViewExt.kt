package com.bozoraka.utils.view

import android.graphics.Paint
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibilityBoolean(isVisible: Boolean?) {
    visibility = if (isVisible == true) View.VISIBLE else View.GONE
}

fun ViewGroup.inflateView(@LayoutRes layout: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToRoot)
}

fun ViewGroup.childViews(): List<View> {
    return (0 until childCount)
            .map { getChildAt(it) }
}

fun View.getColor(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this.context, colorRes)
}

fun TextView?.setTextColorResource(@ColorRes colorRes: Int) {
    this?.setTextColor(getColor(colorRes))
}

fun TextView.setHtmlSpan(html: String?) {
    if (html == null) {
        text = null
        return
    }

    text = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        @Suppress("DEPRECATION")
        Html.fromHtml(html)
    } else {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    }

    movementMethod = LinkMovementMethod.getInstance()
}

fun TextView.setStrikeThrough() {
    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun TextView.setUnderlined() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun EditText.afterTextChange(action: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action.invoke(s?.toString().orEmpty())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun RecyclerView.onOverScroll(triggerCount: Int = 3, action: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager

                if (layoutManager.findLastVisibleItemPosition() >= layoutManager.itemCount - triggerCount) {
                    action()
                }
            }
        }
    })
}

