package com.example.kotlinTeam.common.presentation

import android.content.res.Resources

// перевод из пикселей в dp
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()