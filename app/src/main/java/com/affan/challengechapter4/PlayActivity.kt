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

    private fun getRefreshSelectedUser(){
        batu_player.setBackgroundResource(0)
        kertas_player.setBackgroundResource(0)
        gunting_player.setBackgroundResource(0)
    }

    private fun getRefreshSelectedBot(){
        batu_opponent.setBackgroundResource(0)
        kertas_opponent.setBackgroundResource(0)
        gunting_opponent.setBackgroundResource(0)
    }

    companion object {
        private val playerOne = Person()
        private val playerBot = Bot()
//        val handBot = playerBot.playerHand()

//        fun hassd () : String {
//            return playerBot.playerHand()
//        }

    }

    override fun onClick(p0: View?) {
//        var ksrkd = hassd()

        fun randomHandBot(handi : String) {
            when(handi){
                HandType.A.nameHand -> btnRockBot.setBackgroundResource(R.drawable.select_button)
                HandType.B.nameHand -> btnPaperBot.setBackgroundResource(R.drawable.select_button)
                HandType.C.nameHand -> btnScissorBot.setBackgroundResource(R.drawable.select_button)
            }
        }

//        var fambarTgn = mapOf(
//            HandType.A.nameHand to btnRockBot.setBackgroundResource(R.drawable.select_button),
//            HandType.B.nameHand to btnPaperBot.setBackgroundResource(R.drawable.select_button),
//            HandType.C.nameHand to btnScissorBot.setBackgroundResource(R.drawable.select_button)
//        )
//
//        fun pickRandomBot(option : String) {
//            return fambarTgn[option]!!
//        }

        val arrayHand = arrayOf(
            HandType.A.nameHand,
            HandType.B.nameHand,
            HandType.C.nameHand
        ).random()

        when (p0?.id) {

            R.id.batu_player -> {
                getRefreshSelectedUser()
                getRefreshSelectedBot()
                batu_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.A.nameHand
//                playerBot.playerHand = hassd()

                playerBot.playerHand = arrayHand
                playerOne.attack(playerBot)
                randomHandBot(playerBot.playerHand)
                Log.e("myHand in Activity", playerOne.playerHand)
                Log.e("handBot on Activity", playerBot.playerHand)
            }

            R.id.kertas_player -> {
                getRefreshSelectedUser()
                getRefreshSelectedBot()
                kertas_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.B.nameHand
//                playerBot.playerHand = hassd()
//                hassd()
                playerBot.playerHand = arrayHand
                playerOne.attack(playerBot)
                randomHandBot(playerBot.playerHand)
                Log.e("myHand in Activity", playerOne.playerHand)
                Log.e("handBot on Activity",playerBot.playerHand)
            }

            R.id.gunting_player -> {

                getRefreshSelectedUser()
                getRefreshSelectedBot()
                gunting_player.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.C.nameHand

//                playerBot.playerHand = hassd()
//                hassd()
                playerBot.playerHand = arrayHand
                playerOne.attack(playerBot)
                randomHandBot(playerBot.playerHand)
                Log.e("myHand in Activity", playerOne.playerHand)
                Log.e("handBot on Activity",playerBot.playerHand)
            }

            R.id.btn_back -> {
                val backToLanding = Intent(this,MainActivity::class.java)
                backToLanding.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(backToLanding)
            }

            R.id.btn_refresh -> {
                getRefreshSelectedUser()
                getRefreshSelectedBot()
            }
        }
    }
}