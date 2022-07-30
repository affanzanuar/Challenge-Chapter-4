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

        binding.ivSinglePlayer.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val playerParcelable = PlayerWithParcelable(namePlayer)
            intent.putExtra("PLAYER_NAME",playerParcelable)
            startActivity(intent)
        }

    }

    companion object{
        const val EXTRA_NAME = "extra_name"
    }
}