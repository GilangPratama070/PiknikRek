package com.gilangpratama.piknikrek.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.gilangpratama.piknikrek.R
import com.gilangpratama.piknikrek.databinding.ActivitySplashBinding
import com.gilangpratama.piknikrek.ui.main.MainActivity
import com.gilangpratama.piknikrek.ui.onboard.OnBoardingActivity
import com.gilangpratama.piknikrek.ui.onboard.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val onBoardViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        onBoardViewModel.isFirstTime().observe(this) { isFirstTime ->
            if (!isFirstTime) {
                lifecycleScope.launchWhenCreated { 
                    delay(2000L)
                    runOnUiThread {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                }
            } else {
                lifecycleScope.launchWhenCreated {
                    delay(2000L)
                    runOnUiThread {
                        startActivity(Intent(this@SplashActivity, OnBoardingActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}