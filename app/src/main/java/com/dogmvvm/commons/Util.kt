package com.dogmvvm.commons

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

object Util {
    fun getPlaceholder(): GradientDrawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setColor(Color.parseColor("#FFFFF0")) //ivory
        return drawable
    }
}