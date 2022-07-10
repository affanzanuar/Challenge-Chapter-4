package com.affan.challengechapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvPlayerName : TextView
    private lateinit var btnBack : ImageButton
    private lateinit var btnReset : ImageButton
    private lateinit var btnRockUser : ImageView
    private lateinit var btnPaperUser : ImageView
    private lateinit var btnScissorUser : ImageView
    private lateinit var btnRockBot : ImageView
    private lateinit var btnPaperBot : ImageView
    private lateinit var btnScissorBot : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        getInitComponent()
        getInitListener()

    }

    private fun getInitComponent(){
        tvPlayerName = findViewById(R.id.tv_player_name)
        btnBack = findViewById(R.id.btn_back)
        btnRockUser = findViewById(R.id.batu_player)
        btnPaperUser = findViewById(R.id.kertas_player)
        btnScissorUser = findViewById(R.id.gunting_player)
        btnRockBot = findViewById(R.id.batu_opponent)
        btnPaperBot = findViewById(R.id.kertas_opponent)
        btnScissorBot = findViewById(R.id.gunting_opponent)
        btnReset = findViewById(R.id.btn_refresh)
    }

    private fun getInitListener(){
        btnBack.setOnClickListener(this)
        btnRockUser.setOnClickListener(this)
        btnPaperUser.setOnClickListener(this)
        btnScissorUser.setOnClickListener(this)
        btnReset.setOnClickListener(this)
    }

    private fun getRockSelectedUser(){
        batu_player.setBackgroundResource(R.drawable.select_button)
        kertas_player.setBackgroundResource(R.drawable.reset_background)
        gunting_player.setBackgroundResource(R.drawable.reset_background)
    }

    private fun getScissorSelectedUser(){
        batu_player.setBackgroundResource(R.drawable.reset_background)
        kertas_player.setBackgroundResource(R.drawable.reset_background)
        gunting_player.setBackgroundResource(R.drawable.select_button)
    }

    private fun getPaperSelectedUser(){
        batu_player.setBackgroundResource(R.drawable.reset_background)
        kertas_player.setBackgroundResource(R.drawable.select_button)
        gunting_player.setBackgroundResource(R.drawable.reset_background)
    }

    private fun getRockSelectedBot(){
        batu_opponent.setBackgroundResource(R.drawable.select_button)
        kertas_opponent.setBackgroundResource(R.drawable.reset_background)
        gunting_opponent.setBackgroundResource(R.drawable.reset_background)
    }

    private fun getScissorSelectedBot(){
        batu_opponent.setBackgroundResource(R.drawable.reset_background)
        kertas_opponent.setBackgroundResource(R.drawable.reset_background)
        gunting_opponent.setBackgroundResource(R.drawable.select_button)
    }

    private fun getPaperSelectedBot(){
        batu_opponent.setBackgroundResource(R.drawable.reset_background)
        kertas_opponent.setBackgroundResource(R.drawable.select_button)
        gunting_opponent.setBackgroundResource(R.drawable.reset_background)
    }

    private fun getRefresh(){
        batu_player.setBackgroundResource(R.drawable.reset_background)
        kertas_player.setBackgroundResource(R.drawable.reset_background)
        gunting_player.setBackgroundResource(R.drawable.reset_background)
        batu_opponent.setBackgroundResource(R.drawable.reset_background)
        kertas_opponent.setBackgroundResource(R.drawable.reset_background)
        gunting_opponent.setBackgroundResource(R.drawable.reset_background)
    }

    private val playerBot = Bot()

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.btn_back -> {
                val backToLanding = Intent(this,MainActivity::class.java)
                backToLanding.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(backToLanding)
            }

            R.id.batu_player -> {
                getRockSelectedUser()
                startGameWithBot(HandType.A.nameHand)
            }

            R.id.kertas_player -> {
                getPaperSelectedUser()
                startGameWithBot(HandType.B.nameHand)
            }

            R.id.gunting_player -> {
                getScissorSelectedUser()
                startGameWithBot(HandType.C.nameHand)
            }

            R.id.btn_refresh -> {
                getRefresh()
            }
        }
    }

    private fun startGameWithBot (handType : String){
        when (playerBot.playerHand()){
            HandType.A.nameHand -> getRockSelectedBot()
            HandType.B.nameHand -> getPaperSelectedBot()
            HandType.C.nameHand -> getScissorSelectedBot()
        }

    }
}