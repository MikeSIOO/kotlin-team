package com.example.kotlinTeam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinTeam.databinding.ActivityMainBinding
import com.example.kotlinTeam.feed.presentation.FeedFragment
import com.example.kotlinTeam.onBoarding.presentation.OnBoardingFragment
import com.example.kotlinTeam.profile.presentation.ProfileFragment
import com.example.kotlinTeam.storage.presentation.category.StorageCategoryFragment
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
                .replace(R.id.fragmentContainer, OnBoardingFragment.newInstance())
//                .replace(R.id.fragmentContainer, StorageCategoryFragment.newInstance())
//                .replace(R.id.fragmentContainer, FeedFragment())
                .commitNow()
        }
    }
}
