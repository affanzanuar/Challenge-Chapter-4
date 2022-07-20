package com.affan.challengechapter4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.lifecycle.ViewModelProvider
import com.affan.challengechapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel :MainActivityViewModel
    private val backToVersus = "VS"
    var playerOne : Person = Person ()
    var playerBot : Bot = Bot ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        getInitListener()
        getObserve()
    }

    private fun getInitListener() {
        binding.ivBatuPlayer.setOnClickListener {
            getRefresh()
            viewModel.rockHandPlayer.value = "batu"
            playerOne.playerHand = "batu"
            randomHand()
            viewModel.result.value = playerOne.getAttack(playerBot)
        }

        binding.ivGuntingPlayer.setOnClickListener {
            getRefresh()
            viewModel.scissorHandPlayer.value = "gunting"
            playerOne.playerHand = "gunting"
            randomHand()
            viewModel.result.value = playerOne.getAttack(playerBot)
        }

        binding.ivKertasPlayer.setOnClickListener {
            getRefresh()
            viewModel.paperHandPlayer.value = "kertas"
            playerOne.playerHand = "kertas"
            randomHand()
            viewModel.result.value = playerOne.getAttack(playerBot)
        }

        binding.ivRefresh.setOnClickListener {
            getRefresh()
            binding.tvVersus.text = backToVersus
            viewModel.result.value = backToVersus
        }
    }

    private fun getObserve(){
        viewModel.rockHandPlayer.observe(this) {
            if (viewModel.rockHandPlayer.value == "batu") {
                binding.ivBatuPlayer.setBackgroundResource(R.drawable.select_button)
            }
        }

        viewModel.scissorHandPlayer.observe(this) {
            if (viewModel.scissorHandPlayer.value == "gunting") {
                binding.ivGuntingPlayer.setBackgroundResource(R.drawable.select_button)
            }
        }

        viewModel.paperHandPlayer.observe(this) {
            if (viewModel.paperHandPlayer.value == "kertas") {
                binding.ivKertasPlayer.setBackgroundResource(R.drawable.select_button)
            }
        }

        viewModel.rockHandBot.observe(this) {
            if (viewModel.rockHandBot.value == "batu") {
                binding.ivBatuOpponent.setBackgroundResource(R.drawable.select_button)
            }
        }

        viewModel.scissorHandBot.observe(this) {
            if (viewModel.scissorHandBot.value == "gunting") {
                binding.ivGuntingOpponent.setBackgroundResource(R.drawable.select_button)
            }
        }

        viewModel.paperHandBot.observe(this) {
            if (viewModel.paperHandBot.value == "kertas") {
                binding.ivKertasOpponent.setBackgroundResource(R.drawable.select_button)
            }
        }

        fun getSpannedStyle ( result : String ) : Spanned {
            return Html.fromHtml(result,FROM_HTML_MODE_LEGACY)
        }

        viewModel.result.observe(this){
            when (viewModel.result.value){
                "draw" -> {
                    val draw = getString(R.string.draw)
                    binding.tvVersus.text = getSpannedStyle(draw)
                    binding.tvVersus.setBackgroundResource(R.drawable.draw)
                    binding.tvVersus.setTextColor(Color.WHITE)

                }
                "win" -> {
                    val win = getString(R.string.win)
                    binding.tvVersus.text = getSpannedStyle(win)
                    binding.tvVersus.setBackgroundResource(R.drawable.the_winner)
                    binding.tvVersus.setTextColor(Color.WHITE)
                }
                "lose" -> {
                    val lose = getString(R.string.lose)
                    binding.tvVersus.text = getSpannedStyle(lose)
                    binding.tvVersus.setBackgroundResource(R.drawable.the_winner)
                    binding.tvVersus.setTextColor(Color.WHITE)
                }
                else -> {
                    binding.tvVersus.text = backToVersus
                    binding.tvVersus.setTextColor(Color.RED)
                }
            }
        }
    }

    private fun randomHand (){
        val i = playerBot.getRandomHand()
        playerBot.playerHand = i
        when (playerBot.playerHand){
            "batu" -> viewModel.rockHandBot.value = "batu"
            "gunting" -> viewModel.scissorHandBot.value = "gunting"
            "kertas" -> viewModel.paperHandBot.value = "kertas"
        }
    }

    private fun getRefresh(){
        viewModel.rockHandPlayer.value = null
        viewModel.scissorHandPlayer.value = null
        viewModel.paperHandPlayer.value = null
        viewModel.rockHandBot.value = null
        viewModel.scissorHandBot.value = null
        viewModel.paperHandBot.value = null
        binding.ivBatuPlayer.setBackgroundResource(0)
        binding.ivKertasPlayer.setBackgroundResource(0)
        binding.ivGuntingPlayer.setBackgroundResource(0)
        binding.ivBatuOpponent.setBackgroundResource(0)
        binding.ivKertasOpponent.setBackgroundResource(0)
        binding.ivGuntingOpponent.setBackgroundResource(0)
        binding.tvVersus.setBackgroundResource(0)
    }

//    private fun setInitListener(){
//        binding.batuPlayer.setOnClickListener(this)
//        binding.kertasPlayer.setOnClickListener(this)
//        binding.guntingPlayer.setOnClickListener(this)
//        binding.ivRefresh.setOnClickListener(this)
//    }

//    override fun onClick(p0: View?) {
//        val playerOne = Person()
//        val playerBot = Bot()
//        val randomHand = arrayOf(
//            HandType.A.hand,
//            HandType.B.hand,
//            HandType.C.hand
//        ).random()
//
//        fun getSelectBotHand(hand : String) {
//            when(hand){
//                HandType.A.hand -> binding.batuOpponent.setBackgroundResource(R.drawable.select_button)
//                HandType.B.hand -> binding.kertasOpponent.setBackgroundResource(R.drawable.select_button)
//                HandType.C.hand -> binding.guntingOpponent.setBackgroundResource(R.drawable.select_button)
//            }
//        }
//
//        fun startWithBot(){
//            playerBot.playerHand = randomHand
//            val result = playerOne.attack(playerBot)
//            getSelectBotHand(playerBot.playerHand)
//            Log.d("myHand on Activity", playerOne.playerHand)
//            Log.d("botHand on Activity", playerBot.playerHand)
//            val draw = getString(R.string.draw)
//            val win = getString(R.string.win)
//            val lose = getString(R.string.lose)
//
//            fun getSpannedStyle ( result : String ) : Spanned {
//                return Html.fromHtml(result,FROM_HTML_MODE_LEGACY)
//            }
//
//            return when(result){
//                "draw" -> {
//                    binding.tvVersus.text = getSpannedStyle(draw)
//                    binding.tvVersus.setBackgroundResource(R.drawable.draw)
//                    binding.tvVersus.setTextColor(Color.WHITE)
//                }
//                "win" -> {
//                    binding.tvVersus.text = getSpannedStyle(win)
//                    binding.tvVersus.setBackgroundResource(R.drawable.the_winner)
//                    binding.tvVersus.setTextColor(Color.WHITE)
//                }
//                else -> {
//                    binding.tvVersus.text = getSpannedStyle(lose)
//                    binding.tvVersus.setBackgroundResource(R.drawable.the_winner)
//                    binding.tvVersus.setTextColor(Color.WHITE)
//                }
//            }
//        }
//
//        when (p0?.id) {
//            R.id.batu_player -> {
//                getRefreshSelected()
//                binding.batuPlayer.setBackgroundResource(R.drawable.select_button)
//                playerOne.playerHand = HandType.A.hand
//                startWithBot()
//            }
//
//            R.id.kertas_player -> {
//                getRefreshSelected()
//                binding.kertasPlayer.setBackgroundResource(R.drawable.select_button)
//                playerOne.playerHand = HandType.B.hand
//                startWithBot()
//            }
//
//            R.id.gunting_player -> {
//                getRefreshSelected()
//                binding.guntingPlayer.setBackgroundResource(R.drawable.select_button)
//                playerOne.playerHand = HandType.C.hand
//                startWithBot()
//            }
//
//            R.id.iv_refresh -> {
//                getRefreshSelected()
//                val versus = "VS"
//                binding.tvVersus.text = versus
//                binding.tvVersus.setTextColor(Color.RED)
//            }
//        }
//    }
}