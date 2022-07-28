package com.affan.challengechapter4.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.affan.challengechapter4.databinding.ActivityLandingPageBinding
import com.affan.challengechapter4.view.adapter.ViewPagerAdapter
import com.affan.challengechapter4.view.fragment.FirstLandingPageFragment
import com.affan.challengechapter4.view.fragment.SecondLandingPageFragment
import com.affan.challengechapter4.view.fragment.ThirdLandingPageFragment

class LandingPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment : ArrayList<Fragment> = arrayListOf(
            FirstLandingPageFragment(),
            SecondLandingPageFragment(),
            ThirdLandingPageFragment(),
        )
        ViewPagerAdapter(fragment,this).also {
            binding.viewPager.adapter = it
        }
    }
}