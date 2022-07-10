package com.affan.challengechapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnSinglePlayer : Button
    private lateinit var btnMultiPlayer : Button
    private lateinit var btnHelp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()

        btnSinglePlayer.setOnClickListener(this)
        btnMultiPlayer.setOnClickListener(this)
        btnHelp.setOnClickListener(this)

    }

    private fun initComponent(){
        btnSinglePlayer = findViewById(R.id.btn_single_player)
        btnMultiPlayer = findViewById(R.id.btn_multi_player)
        btnHelp = findViewById(R.id.btn_help)
    }

    override fun onClick(p0: View?) {
        when ( p0?.id ) {
            R.id.btn_single_player -> {
                val singlePlayerActivity = Intent(this,PlayActivity::class.java)
                startActivity(singlePlayerActivity)
            }

            R.id.btn_multi_player -> {

            }

            R.id.btn_help -> {
                val helpButtonActivity = Intent(this,HelpActivity::class.java)
                startActivity(helpButtonActivity)
            }
        }
    }
}