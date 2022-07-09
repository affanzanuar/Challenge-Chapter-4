package com.affan.challengechapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PlayActivity : AppCompatActivity() {

    lateinit var tvPlayerName : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        initComponent()

        val playerName = intent.getStringExtra(PLAYER_NAME)
        tvPlayerName.text = playerName.toString()

    }

    private fun initComponent(){
        tvPlayerName = findViewById(R.id.tv_player_name)
    }

    companion object{
        const val PLAYER_NAME = "player_name"
    }
}