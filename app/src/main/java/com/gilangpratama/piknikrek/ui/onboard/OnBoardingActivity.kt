package com.gilangpratama.piknikrek.ui.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.gilangpratama.piknikrek.databinding.ActivityOnBoardingBinding
import com.gilangpratama.piknikrek.ui.adapters.SectionPagerAdapter
import com.gilangpratama.piknikrek.ui.main.MainActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private val onBoardViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        setUpViewPager()
    }

    private fun setUpView() {
        onBoardViewModel.isFirstTime().observe(this) { isFirstTime ->
            if (!isFirstTime) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun setUpViewPager() {
        binding.apply {
            val sectionAdapter = SectionPagerAdapter(supportFragmentManager, lifecycle)
            viewPager.adapter = sectionAdapter
            TabLayoutMediator(tabs, viewPager) { _, _ -> }.attach()
        }
    }
}