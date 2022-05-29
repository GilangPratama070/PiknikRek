package com.gilangpratama.piknikrek.ui.onboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gilangpratama.piknikrek.databinding.ActivityOnBoardingBinding
import com.gilangpratama.piknikrek.ui.adapters.SectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.apply {
            val sectionAdapter = SectionPagerAdapter(supportFragmentManager, lifecycle)
            viewPager.adapter = sectionAdapter
            TabLayoutMediator(tabs, viewPager) { _, _ -> }.attach()
        }
    }
}