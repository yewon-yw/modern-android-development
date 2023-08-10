package com.example.modernandroiddevelopment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.modernandroiddevelopment.databinding.ActivityMainBinding
import com.example.modernandroiddevelopment.ui.view.FavoriteFragment
import com.example.modernandroiddevelopment.ui.view.SearchFragment
import com.example.modernandroiddevelopment.ui.view.SettingsFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationView()
        // 앱이 처음 실행됐을 경우에만 search fragment로 설정
        if(savedInstanceState == null){
            binding.bottomNavigationView.selectedItemId = R.id.fragment_search
        }
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.fragment_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SearchFragment())
                        .commit()
                    true
                }
                R.id.fragment_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavoriteFragment())
                        .commit()
                    true
                }
                R.id.fragment_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, SettingsFragment())
                        .commit()
                    true
                }
                else -> false
            }

        }
    }
}