package com.affan.challengechapter4.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.affan.challengechapter4.R
import com.affan.challengechapter4.databinding.ActivityMenuBinding
import com.affan.challengechapter4.model.user.PlayerWithParcelable
import com.affan.challengechapter4.model.user.PlayerWithSerializable
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNameSerializable()
        setCustomSnackBar(getNameSerializable())
        getOnClickListener()
    }

    private fun getOnClickListener(){
        binding.ivMultiPlayer.setOnClickListener {
            setNamePlayerAndCategory(true)
        }

        binding.tvUserVsUser.setOnClickListener {
            setNamePlayerAndCategory(true)
        }

        binding.ivSinglePlayer.setOnClickListener {
            setNamePlayerAndCategory(false)
        }

        binding.tvUserVsCom.setOnClickListener{
            setNamePlayerAndCategory(false)
        }
    }

    /**
    Passing data player name with Parcelable to MainActivity and game category (SinglePlayer
    or MultiPlayer) with Bundle to MainActivity
    **/
    private fun setNamePlayerAndCategory (gameCategory : Boolean){
        val intent = Intent(this,MainActivity::class.java)
        val playerParcelable = PlayerWithParcelable(getNameSerializable())
        val bundle = Bundle()
        bundle.putBoolean(EXTRA_CATEGORY, gameCategory)
        intent.putExtra(EXTRA_NAME_PARCELABLE, playerParcelable)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    // Receive data Serializable from LandingPageActivity
    private fun getNameSerializable () : String {
        val personSerializable = intent.getSerializableExtra(EXTRA_NAME_SERIALIZABLE)
                as PlayerWithSerializable
        val namePlayer = personSerializable.name
        val nameVsPlayer = "$namePlayer vs ${getString(R.string.pemain)}"
        val nameVsCom = "$namePlayer vs ${getString(R.string.cpu)}"
        binding.tvUserVsUser.text = nameVsPlayer
        binding.tvUserVsCom.text = nameVsCom
        return namePlayer
    }

    private fun setCustomSnackBar (message : String) {
        val snackBar = binding.root.let {
            Snackbar.make(
                it,
                "${getString(R.string.welcome_snackbar)} $message",
                Snackbar.LENGTH_INDEFINITE)
        }
        snackBar.setAction(getString(R.string.close_snackbar)) { snackBar.dismiss() }
        snackBar.setActionTextColor(applicationContext.getColor(R.color.button_dialod_2))
        snackBar.show()
    }

    companion object{
        const val EXTRA_NAME_SERIALIZABLE = "extra_name_serializable"
        const val EXTRA_NAME_PARCELABLE = "extra_name_serializable"
        const val EXTRA_CATEGORY = "extra_category"
    }
}