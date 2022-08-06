package com.affan.challengechapter4.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.affan.challengechapter4.databinding.ActivityLandingPageBinding
import com.affan.challengechapter4.model.user.PlayerWithSerializable
import com.affan.challengechapter4.view.adapter.ViewPagerAdapter
import com.affan.challengechapter4.view.fragment.FirstLandingPageFragment
import com.affan.challengechapter4.view.fragment.SecondLandingPageFragment
import com.affan.challengechapter4.view.fragment.ThirdLandingPageFragment

class LandingPageActivity : AppCompatActivity(), ThirdLandingPageFragment.ThirdListener {
    private lateinit var binding: ActivityLandingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getFragments()
        binding.circleIndicator.setViewPager(binding.viewPager)
    }

    private fun getFragments () {
        val fragment : ArrayList<Fragment> = arrayListOf(
            FirstLandingPageFragment(),
            SecondLandingPageFragment(),
            ThirdLandingPageFragment(),
        )
        ViewPagerAdapter(fragment,this).also {
            binding.viewPager.adapter = it
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is ThirdLandingPageFragment){
            fragment.setListener(this)
        }
    }

    override fun textFromThirdFragment(name: String) {
        val intent = Intent(this,MenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //Passing data with Serializable to MenuActivity
        val playerSerializable = PlayerWithSerializable(name)
        intent.putExtra(MenuActivity.EXTRA_NAME_SERIALIZABLE,playerSerializable)
        startActivity(intent)
    }
}