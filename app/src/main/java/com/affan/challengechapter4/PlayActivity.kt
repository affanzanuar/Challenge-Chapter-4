package com.affan.challengechapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private lateinit var ivVersus : ImageView

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
        ivVersus = findViewById(R.id.iv_versus)
    }

    private fun getInitListener(){
        btnBack.setOnClickListener(this)
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
            HandType.A.nameHand,
            HandType.B.nameHand,
            HandType.C.nameHand
        ).random()

        fun pickSelectRandomHandBot(hand : String) {
            when(hand){
                HandType.A.nameHand -> btnRockBot.setBackgroundResource(R.drawable.select_button)
                HandType.B.nameHand -> btnPaperBot.setBackgroundResource(R.drawable.select_button)
                HandType.C.nameHand -> btnScissorBot.setBackgroundResource(R.drawable.select_button)
            }
        }

        fun startWithBot(){
            playerBot.playerHand = randomHand
            val result = playerOne.attack(playerBot)
            pickSelectRandomHandBot(playerBot.playerHand)
            Log.e("player one on Activity", playerOne.playerHand)
            Log.e("player bot on Activity", playerBot.playerHand)

            when(result){
                "draw" -> ivVersus.setImageResource(R.drawable.draw)
                "win" -> ivVersus.setImageResource(R.drawable.win)
                "lose" -> ivVersus.setImageResource(R.drawable.lose)
            }
        }

        when (p0?.id) {
            R.id.btn_back -> {
                val backToLanding = Intent(this,MainActivity::class.java)
                backToLanding.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(backToLanding)
            }

            R.id.batu_player -> {
                getRefreshSelected()
                batu_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.A.nameHand
                startWithBot()
            }

            R.id.kertas_player -> {
                getRefreshSelected()
                kertas_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.B.nameHand
                startWithBot()
            }

            R.id.gunting_player -> {
                getRefreshSelected()
                gunting_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.C.nameHand
                startWithBot()
            }

            R.id.btn_refresh -> {
                getRefreshSelected()
                ivVersus.setImageResource(R.drawable.versus)
            }
        }
    }
}