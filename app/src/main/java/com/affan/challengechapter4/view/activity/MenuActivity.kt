package com.affan.challengechapter4.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.challengechapter4.R
import com.affan.challengechapter4.databinding.ActivityMenuBinding
import com.affan.challengechapter4.model.user.PlayerWithParcelable
import com.affan.challengechapter4.model.user.PlayerWithSerializable

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNameSerializable()
        getOnClickListener()
    }

    private fun getOnClickListener(){
        binding.ivMultiPlayer.setOnClickListener {
            setNamePlayerAndCategory(true)
        }

        binding.tvUserVsUser.setOnClickListener {
            setNamePlayerAndCategory(true)
        }

        binding.ivSinglePlayer.setOnClickListener {
            setNamePlayerAndCategory(false)
        }

        binding.tvUserVsCom.setOnClickListener{
            setNamePlayerAndCategory(false)
        }
    }

    private fun setNamePlayerAndCategory (gameCategory : Boolean){
        val intent = Intent(this,MainActivity::class.java)
        // Passing data player name with Parcelable to MainActivity
        val playerParcelable = PlayerWithParcelable(getNameSerializable())
        // Passing data game category with Bundle to MainActivity
        val bundle = Bundle()
        bundle.putBoolean(EXTRA_CATEGORY, gameCategory)
        intent.putExtra(EXTRA_NAME_PARCELABLE, playerParcelable)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun getNameSerializable () : String {
        // Receive data Serializable from LandingPageActivity
        val personSerializable = intent.getSerializableExtra(EXTRA_NAME_SERIALIZABLE)
                as PlayerWithSerializable
        val namePlayer = personSerializable.name
        val nameVsPlayer = "$namePlayer vs ${getString(R.string.pemain)}"
        val nameVsCom = "$namePlayer vs ${getString(R.string.cpu)}"
        binding.tvUserVsUser.text = nameVsPlayer
        binding.tvUserVsCom.text = nameVsCom
        return namePlayer
    }

    companion object{
        const val EXTRA_NAME_SERIALIZABLE = "extra_name_serializable"
        const val EXTRA_NAME_PARCELABLE = "extra_name_serializable"
        const val EXTRA_CATEGORY = "extra_category"
    }
}