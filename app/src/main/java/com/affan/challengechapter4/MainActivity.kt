package com.affan.challengechapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvPlayerName : TextView
    private lateinit var btnReset : ImageView
    private lateinit var btnRockUser : ImageView
    private lateinit var btnPaperUser : ImageView
    private lateinit var btnScissorUser : ImageView
    private lateinit var btnRockBot : ImageView
    private lateinit var btnPaperBot : ImageView
    private lateinit var btnScissorBot : ImageView
    private lateinit var ivVersus : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInitComponent()
        getInitListener()
    }

    private fun getInitComponent(){
        tvPlayerName = findViewById(R.id.tv_player_name)
        btnRockUser = findViewById(R.id.batu_player)
        btnPaperUser = findViewById(R.id.kertas_player)
        btnScissorUser = findViewById(R.id.gunting_player)
        btnRockBot = findViewById(R.id.batu_opponent)
        btnPaperBot = findViewById(R.id.kertas_opponent)
        btnScissorBot = findViewById(R.id.gunting_opponent)
        btnReset = findViewById(R.id.iv_refresh)
        ivVersus = findViewById(R.id.iv_versus)
    }

    private fun getInitListener(){
        btnRockUser.setOnClickListener(this)
        btnPaperUser.setOnClickListener(this)
        btnScissorUser.setOnClickListener(this)
        btnReset.setOnClickListener(this)
    }

    private fun getRefreshSelected(){
        batu_player.setBackgroundResource(0)
        kertas_player.setBackgroundResource(0)
        gunting_player.setBackgroundResource(0)
        batu_opponent.setBackgroundResource(0)
        kertas_opponent.setBackgroundResource(0)
        gunting_opponent.setBackgroundResource(0)
    }

    override fun onClick(p0: View?) {
        val playerOne = Person()
        val playerBot = Bot()
        val randomHand = arrayOf(
            HandType.A.hand,
            HandType.B.hand,
            HandType.C.hand
        ).random()

        fun getSelectBotHand(hand : String) {
            when(hand){
                HandType.A.hand -> btnRockBot.setBackgroundResource(R.drawable.select_button)
                HandType.B.hand -> btnPaperBot.setBackgroundResource(R.drawable.select_button)
                HandType.C.hand -> btnScissorBot.setBackgroundResource(R.drawable.select_button)
            }
        }

        fun startWithBot(){
            playerBot.playerHand = randomHand
            val result = playerOne.attack(playerBot)
            getSelectBotHand(playerBot.playerHand)
            Log.e("myHand on Activity", playerOne.playerHand)
            Log.e("botHand on Activity", playerBot.playerHand)

            when(result){
                "draw" -> ivVersus.setImageResource(R.drawable.draw)
                "win" -> ivVersus.setImageResource(R.drawable.player_satu_win)
                "lose" -> ivVersus.setImageResource(R.drawable.player_dua_win)
            }
        }

        when (p0?.id) {

            R.id.batu_player -> {
                getRefreshSelected()
                batu_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.A.hand
                startWithBot()
            }

            R.id.kertas_player -> {
                getRefreshSelected()
                kertas_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.B.hand
                startWithBot()
            }

            R.id.gunting_player -> {
                getRefreshSelected()
                gunting_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.C.hand
                startWithBot()
            }

            R.id.iv_refresh -> {
                getRefreshSelected()
                ivVersus.setImageResource(R.drawable.versus)
            }
        }
    }
}