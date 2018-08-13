package com.example.chiachen.ktmvp.utils

import android.app.Activity
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.chiachen.ktmvp.MyApp

val Activity.myApp: MyApp
    get() = application as MyApp

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun isLollipopOrAbove(func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        func
    }
}
