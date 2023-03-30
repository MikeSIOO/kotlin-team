package com.example.kotkin_team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotkin_team.databinding.ActivityMainBinding
import com.example.kotkin_team.profile.presentation.ProfileFragment
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
                .replace(R.id.fragmentContainer, ProfileFragment.newInstance())
                .commitNow()
        }
    }
}
