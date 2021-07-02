package com.trycatch.til.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageURI")
fun ImageView.setImageUri(uri: Uri) {
    Glide.with(this)
        .load(uri)
        .into(this)
}
