package com.example.kotlinTeam.common.presentation

import android.content.res.Resources

// перевод из пикселей в dp
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

// перевод из пикселей в pt по вертикали
val Int.ptY: Int
    get() = (this * Resources.getSystem().displayMetrics.heightPixels / 100)

// перевод из пикселей в pt по вертикали
val Int.ptX: Int
    get() = (this * Resources.getSystem().displayMetrics.widthPixels / 100)
