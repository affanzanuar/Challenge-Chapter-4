package com.affan.challengechapter4.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.challengechapter4.databinding.ActivitySplashScreenBinding
import com.affan.challengechapter4.utility.Utility
import com.bumptech.glide.Glide

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(Utility.SPLASH_SCREEN_URL)
            .into(binding.ivFirstSplashScreen)

        binding.ivSecondSplashScreen.alpha = 0f
        binding.ivSecondSplashScreen.animate().setDuration(3000).alpha(1f).withEndAction{
            val intent = Intent(this,LandingPageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}