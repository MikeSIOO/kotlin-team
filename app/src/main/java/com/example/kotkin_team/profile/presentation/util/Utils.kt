package com.example.kotkin_team.profile.presentation.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.kotkin_team.R

fun bindImage(parentView: View, imageUrl: String, imageView: ImageView) {
    parentView.let {
        Glide.with(it)
            .load(imageUrl)
            .override(Target.SIZE_ORIGINAL)
            .into(imageView)
        imageView.setBackgroundColor(parentView.context.getColor(R.color.orange_700))
    }
}
