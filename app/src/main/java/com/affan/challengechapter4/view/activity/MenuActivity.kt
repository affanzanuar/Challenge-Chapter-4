package com.affan.challengechapter4.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.challengechapter4.databinding.ActivityMenuBinding
import com.affan.challengechapter4.model.user.PlayerWithParcelable
import com.affan.challengechapter4.model.user.PlayerWithSerializable

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive data Serializable from LandingPageActivity
        val personSerializable = intent.getSerializableExtra(EXTRA_NAME_SERIALIZABLE)
                as PlayerWithSerializable
        val namePlayer = personSerializable.name
        val nameVsPlayer = "$namePlayer vs Pemain"
        val nameVsCom = "$namePlayer vs CPU"
        binding.tvUserVsUser.text = nameVsPlayer
        binding.tvUserVsCom.text = nameVsCom

        fun getIntentPlayer (gameCategory : Int){
            val intent = Intent(this,MainActivity::class.java)
            // Passing data player name with Parcelable to MainActivity
            val playerParcelable = PlayerWithParcelable(namePlayer)
            // Passing data game category with Bundle to MainActivity
            val bundle = Bundle()
            bundle.putInt(GAME_CATEGORY, gameCategory)
            intent.putExtra(EXTRA_NAME_PARCELABLE,playerParcelable)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        binding.ivMultiPlayer.setOnClickListener {
            getIntentPlayer(1)
        }

        binding.tvUserVsUser.setOnClickListener {
            getIntentPlayer(1)
        }

        binding.ivSinglePlayer.setOnClickListener {
            getIntentPlayer(2)
        }

        binding.tvUserVsCom.setOnClickListener{
            getIntentPlayer(2)
        }
    }

    companion object{
        const val EXTRA_NAME_SERIALIZABLE = "extra_name_serializable"
        const val GAME_CATEGORY = "game_category"
        const val EXTRA_NAME_PARCELABLE = "extra_name_serializable"
    }
}