package com.example.kotkin_team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotkin_team.products.presentation.ProductsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, ProductsFragment())
                .replace(R.id.container, ProductsFragment.newInstance(page))
                .commitNow()
        }
    }
}