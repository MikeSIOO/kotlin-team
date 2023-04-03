package com.example.kotkin_team.profile.presentation.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

fun bindImage(parentView: View, imageUrl: String, imageView: ImageView) {
    parentView.let {
        Glide.with(it)
            .load(imageUrl)
            .override(Target.SIZE_ORIGINAL)
            .into(imageView)
        imageView.setBackgroundColor(0xD0D0D000.toInt())
    }
}
