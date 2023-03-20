package com.example.kotkin_team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotkin_team.storage.presentation.category.StorageCategoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StorageCategoryFragment.newInstance())
                .commit()
        }
    }
}