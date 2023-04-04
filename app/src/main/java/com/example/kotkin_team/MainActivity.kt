package com.example.kotkin_team

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotkin_team.databinding.ActivityMainBinding
import com.example.kotkin_team.feed.presentation.FeedFragment
import com.example.kotkin_team.profile.presentation.ProfileFragment
import com.example.kotkin_team.storage.presentation.category.StorageCategoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
//                    TODO Раскоментить строку с нужным фрагментом !
//                .replace(R.id.fragmentContainer, ProfileFragment.newInstance())
                .replace(R.id.fragmentContainer, StorageCategoryFragment.newInstance())
//                .replace(R.id.fragmentContainer, FeedFragment())
                .commitNow()
        }
    }
}
