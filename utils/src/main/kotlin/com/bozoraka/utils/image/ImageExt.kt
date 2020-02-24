package com.bozoraka.utils.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlin.math.min

fun ImageView.loadFromUrl(url: String?, maxWidth: Int = 1280, maxHeight: Int = 1280) {
    if (url.isNullOrBlank()) {
        setImageDrawable(null)
        return
    }

    // Resize large bitmaps
    val transformer = BitmapTransformer(maxWidth, maxHeight)

    Picasso.get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .transform(transformer)

    Picasso.get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .transform(transformer)
        .into(this, object : Callback {
            override fun onSuccess() {
                // no-op
            }

            override fun onError(e: Exception) {
                // Try again online if cache failed
                Picasso.get()
                    .load(url)
                    .transform(transformer)
                    .into(this@loadFromUrl)
            }
        })
}

fun ImageView.loadWithCenterCrop(
    url: String?,
    placeholder: Drawable? = null,
    errorDrawable: Drawable? = null
) {
    if (url.orEmpty().isBlank()) {
        setImageDrawable(placeholder)
        return
    }

    val cacheReq = Picasso.get()
        .load(url)
        .fit()
        .centerCrop()
        .networkPolicy(NetworkPolicy.OFFLINE)

    val req = Picasso.get()
        .load(url)
        .fit()
        .centerCrop()

    if (placeholder != null) {
        cacheReq.placeholder(placeholder)
        req.placeholder(placeholder)
    }

    if (errorDrawable != null) {
        cacheReq.error(errorDrawable)
        req.error(errorDrawable)
    }

    cacheReq.into(this, object : Callback {
        override fun onSuccess() {
            // no-op
        }

        override fun onError(e: Exception) {
            // Try again online if cache failed
            req.into(this@loadWithCenterCrop)
        }
    })
}

class BitmapTransformer(
    private val maxWidth: Int,
    private val maxHeight: Int
) : Transformation {

    override fun key(): String = "maxWidth:$maxWidth-maxHeight:$maxHeight"

    override fun transform(source: Bitmap): Bitmap {
        if (source.width == 0 || source.height == 0) {
            return source
        }

        if (source.width > maxWidth || source.height > maxHeight) {

            val requiredWidthScale = maxWidth.toFloat() / source.width
            val requiredHeightScale = maxHeight.toFloat() / source.height
            val scale = min(requiredWidthScale, requiredHeightScale)
            val outWidth = (scale * source.width).toInt()
            val outHeight = (scale * source.height).toInt()

            val transformedBitmap = Bitmap.createScaledBitmap(source, outWidth, outHeight, true)
            source.recycle()

            return transformedBitmap
        }

        return source
    }
}