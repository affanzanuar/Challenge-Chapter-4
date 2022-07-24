package com.affan.challengechapter4.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.affan.challengechapter4.*
import com.affan.challengechapter4.model.user.Bot
import com.affan.challengechapter4.model.user.Person
import com.affan.challengechapter4.data.HandType
import com.affan.challengechapter4.data.ResultType
import com.affan.challengechapter4.databinding.ActivityMainBinding
import com.affan.challengechapter4.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var playerOne : Person = Person ()
    private var playerBot : Bot = Bot ()
    private val viewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getClickListener()
        getObserve()
    }

    private fun getClickListener() {
        binding.ivBatuPlayer.setOnClickListener {
            getRefreshBackground()
            viewModel.getRefreshViewModel()
            viewModel.setRockHandPlayer()
            playerOne.playerHand = HandType.ROCK.hand
            getRandomHand()
            viewModel.setResult(playerOne.getAttack(playerBot))
        }

        binding.ivGuntingPlayer.setOnClickListener {
            getRefreshBackground()
            viewModel.getRefreshViewModel()
            viewModel.setScissorHandPlayer()
            playerOne.playerHand = HandType.SCISSOR.hand
            getRandomHand()
            viewModel.setResult(playerOne.getAttack(playerBot))
        }

        binding.ivKertasPlayer.setOnClickListener {
            getRefreshBackground()
            viewModel.getRefreshViewModel()
            viewModel.setPaperHandPlayer()
            playerOne.playerHand = HandType.PAPER.hand
            getRandomHand()
            viewModel.setResult(playerOne.getAttack(playerBot))
        }

        binding.ivRefresh.setOnClickListener {
            getRefreshBackground()
            viewModel.getRefreshViewModel()
            binding.tvVersus.text = ResultType.DEFAULT.result
            viewModel.setResult(ResultType.DEFAULT.result)
        }
    }

    private fun getRandomHand (){
        playerBot.getRandomHandBot().also {
            playerBot.playerHand = it
        }
        when (playerBot.playerHand){
            HandType.ROCK.hand -> viewModel.setRockHandOpponent()
            HandType.SCISSOR.hand -> viewModel.setScissorHandOpponent()
            HandType.PAPER.hand -> viewModel.setPaperHandOpponent()
        }
    }

    private fun getObserve(){
        fun getSpannedStyle (result : String) : Spanned {
            return Html.fromHtml(result,FROM_HTML_MODE_LEGACY)
        }

        viewModel.handPlayer.observe(this) {
            when (viewModel.handPlayer.value){
                HandType.ROCK.hand -> {
                    binding.ivBatuPlayer.setBackgroundResource(R.drawable.bg_select_button)
                }
                HandType.SCISSOR.hand -> {
                    binding.ivGuntingPlayer.setBackgroundResource(R.drawable.bg_select_button)
                }
                HandType.PAPER.hand -> {
                    binding.ivKertasPlayer.setBackgroundResource(R.drawable.bg_select_button)
                }
            }
        }

        viewModel.handOpponent.observe(this) {
            when (viewModel.handOpponent.value) {
                HandType.ROCK.hand -> {
                    binding.ivBatuOpponent.setBackgroundResource(R.drawable.bg_select_button)
                }
                HandType.SCISSOR.hand -> {
                    binding.ivGuntingOpponent.setBackgroundResource(R.drawable.bg_select_button)
                }
                HandType.PAPER.hand -> {
                    binding.ivKertasOpponent.setBackgroundResource(R.drawable.bg_select_button)
                }
            }
        }

        viewModel.result.observe(this){
            when (viewModel.result.value){
                ResultType.DRAW.result -> {
                    val draw = getString(R.string.draw)
                    binding.tvVersus.text = getSpannedStyle(draw)
                    binding.tvVersus.setBackgroundResource(R.drawable.bg_draw)
                }
                ResultType.WIN.result -> {
                    val win = getString(R.string.win)
                    binding.tvVersus.text = getSpannedStyle(win)
                    binding.tvVersus.setBackgroundResource(R.drawable.bg_winner)
                }
                ResultType.LOSE.result -> {
                    val lose = getString(R.string.lose)
                    binding.tvVersus.text = getSpannedStyle(lose)
                    binding.tvVersus.setBackgroundResource(R.drawable.bg_winner)
                }
            }
        }
    }

    private fun getRefreshBackground(){
        binding.ivBatuPlayer.setBackgroundResource(0)
        binding.ivKertasPlayer.setBackgroundResource(0)
        binding.ivGuntingPlayer.setBackgroundResource(0)
        binding.ivBatuOpponent.setBackgroundResource(0)
        binding.ivKertasOpponent.setBackgroundResource(0)
        binding.ivGuntingOpponent.setBackgroundResource(0)
        binding.tvVersus.setBackgroundResource(0)
    }
}