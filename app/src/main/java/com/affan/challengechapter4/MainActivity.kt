package com.affan.challengechapter4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.affan.challengechapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setInitListener()
    }

    private fun getRefreshSelected(){
        binding.batuPlayer.setBackgroundResource(0)
        binding.kertasPlayer.setBackgroundResource(0)
        binding.guntingPlayer.setBackgroundResource(0)
        binding.batuOpponent.setBackgroundResource(0)
        binding.kertasOpponent.setBackgroundResource(0)
        binding.guntingOpponent.setBackgroundResource(0)
        binding.tvVersus.setBackgroundResource(0)
    }

    private fun setInitListener(){
        binding.batuPlayer.setOnClickListener(this)
        binding.kertasPlayer.setOnClickListener(this)
        binding.guntingPlayer.setOnClickListener(this)
        binding.ivRefresh.setOnClickListener(this)
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
                HandType.A.hand -> binding.batuOpponent.setBackgroundResource(R.drawable.select_button)
                HandType.B.hand -> binding.kertasOpponent.setBackgroundResource(R.drawable.select_button)
                HandType.C.hand -> binding.guntingOpponent.setBackgroundResource(R.drawable.select_button)
            }
        }

        fun startWithBot(){
            playerBot.playerHand = randomHand
            val result = playerOne.attack(playerBot)
            getSelectBotHand(playerBot.playerHand)
            Log.d("myHand on Activity", playerOne.playerHand)
            Log.d("botHand on Activity", playerBot.playerHand)
            val draw = getString(R.string.draw)
            val win = getString(R.string.win)
            val lose = getString(R.string.lose)

            fun getSpannedStyle ( result : String ) : Spanned {
                return Html.fromHtml(result,FROM_HTML_MODE_LEGACY)
            }

            return when(result){
                "draw" -> {
                    binding.tvVersus.text = getSpannedStyle(draw)
                    binding.tvVersus.setBackgroundResource(R.drawable.draw)
                    binding.tvVersus.setTextColor(Color.WHITE)
                }
                "win" -> {
                    binding.tvVersus.text = getSpannedStyle(win)
                    binding.tvVersus.setBackgroundResource(R.drawable.the_winner)
                    binding.tvVersus.setTextColor(Color.WHITE)
                }
                else -> {
                    binding.tvVersus.text = getSpannedStyle(lose)
                    binding.tvVersus.setBackgroundResource(R.drawable.the_winner)
                    binding.tvVersus.setTextColor(Color.WHITE)
                }
            }
        }

        when (p0?.id) {
            R.id.batu_player -> {
                getRefreshSelected()
                binding.batuPlayer.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.A.hand
                startWithBot()
            }

            R.id.kertas_player -> {
                getRefreshSelected()
                binding.kertasPlayer.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.B.hand
                startWithBot()
            }

            R.id.gunting_player -> {
                getRefreshSelected()
                binding.guntingPlayer.setBackgroundResource(R.drawable.select_button)
                playerOne.playerHand = HandType.C.hand
                startWithBot()
            }

            R.id.iv_refresh -> {
                getRefreshSelected()
                val versus = "VS"
                binding.tvVersus.text = versus
                binding.tvVersus.setTextColor(Color.RED)
            }
        }
    }
}