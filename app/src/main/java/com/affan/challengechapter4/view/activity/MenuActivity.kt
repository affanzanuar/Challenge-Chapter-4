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

        val personSerializable = intent.getSerializableExtra(EXTRA_NAME) as PlayerWithSerializable
        val namePlayer = personSerializable.name
        val nameVsPlayer = "$namePlayer vs Pemain"
        val nameVsCom = "$namePlayer vs CPU"
        binding.tvUserVsUser.text = nameVsPlayer
        binding.tvUserVsCom.text = nameVsCom

        fun getIntentPlayer (gameCategory : Int){
            val intent = Intent(this,MainActivity::class.java)
            //passing data with Parcelable
            val playerParcelable = PlayerWithParcelable(namePlayer)
            //passing data with Bundle
            val bundle = Bundle()
            bundle.putInt(GAME_CATEGORY, gameCategory)
            intent.putExtra(PLAYER_NAME_PRCLB,playerParcelable)
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
        const val EXTRA_NAME = "extra_name"
        const val GAME_CATEGORY = "game_category"
        const val PLAYER_NAME_PRCLB = "player_name"
    }
}