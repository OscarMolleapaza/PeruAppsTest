package com.omolleapaza.peruapps.peruappstest.ui.databinding

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebView
import androidx.databinding.BindingAdapter

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

@BindingAdapter("loadUrl")
fun loadUrl(view: WebView, url: String?) {
    url?.let {
        view.loadUrl(it)
    }

}