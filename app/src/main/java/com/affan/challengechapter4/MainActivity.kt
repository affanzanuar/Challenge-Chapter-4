package com.affan.challengechapter4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnPlay : Button
    private lateinit var edtInputName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()

        btnPlay.setOnClickListener(this)

    }

    private fun initComponent(){
        btnPlay = findViewById(R.id.btn_play)
        edtInputName = findViewById(R.id.edt_input_name)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btn_play){
            var isEmptyName = false
            val inputName = edtInputName.text.toString()

            if (inputName.isEmpty()){
                isEmptyName = true
                edtInputName.error = "This field must be filled"
            }

            if (!isEmptyName){
                val playActivity = Intent(this,PlayActivity::class.java)
                playActivity.putExtra(PlayActivity.PLAYER_NAME,inputName)
                startActivity(playActivity)
            }

        }
    }

    companion object{
        const val INPUT_NAME = "input_name"
    }
}