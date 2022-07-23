package com.affan.challengechapter4.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
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
            getRefreshViewModel()
            getRandomHand()
            viewModel.rockHandPlayer.value = HandType.ROCK.hand
            playerOne.playerHand = HandType.ROCK.hand
            viewModel.result.value = playerOne.getAttack(playerBot)
        }

        binding.ivGuntingPlayer.setOnClickListener {
            getRefreshBackground()
            getRefreshViewModel()
            getRandomHand()
            viewModel.scissorHandPlayer.value = HandType.SCISSOR.hand
            playerOne.playerHand = HandType.SCISSOR.hand
            viewModel.result.value = playerOne.getAttack(playerBot)
        }

        binding.ivKertasPlayer.setOnClickListener {
            getRefreshBackground()
            getRefreshViewModel()
            getRandomHand()
            viewModel.paperHandPlayer.value = HandType.PAPER.hand
            playerOne.playerHand = HandType.PAPER.hand
            viewModel.result.value = playerOne.getAttack(playerBot)
        }

        binding.ivRefresh.setOnClickListener {
            getRefreshBackground()
            getRefreshViewModel()
            binding.tvVersus.text = ResultType.DEFAULT.result
            viewModel.result.value = ResultType.DEFAULT.result
        }
    }

    private fun getRandomHand (){
        val random = playerBot.getRandomHandBot()
        playerBot.playerHand = random
        when (playerBot.playerHand){
            HandType.ROCK.hand -> viewModel.rockHandBot.value = HandType.ROCK.hand
            HandType.SCISSOR.hand -> viewModel.scissorHandBot.value = HandType.SCISSOR.hand
            HandType.PAPER.hand -> viewModel.paperHandBot.value = HandType.PAPER.hand
        }
    }

    private fun getObserve(){
        viewModel.rockHandPlayer.observe(this) {
            if (viewModel.rockHandPlayer.value == HandType.ROCK.hand) {
                binding.ivBatuPlayer.setBackgroundResource(R.drawable.bg_select_button)
            }
        }

        viewModel.scissorHandPlayer.observe(this) {
            if (viewModel.scissorHandPlayer.value == HandType.SCISSOR.hand) {
                binding.ivGuntingPlayer.setBackgroundResource(R.drawable.bg_select_button)
            }
        }

        viewModel.paperHandPlayer.observe(this) {
            if (viewModel.paperHandPlayer.value == HandType.PAPER.hand) {
                binding.ivKertasPlayer.setBackgroundResource(R.drawable.bg_select_button)
            }
        }

        viewModel.rockHandBot.observe(this) {
            if (viewModel.rockHandBot.value == HandType.ROCK.hand) {
                binding.ivBatuOpponent.setBackgroundResource(R.drawable.bg_select_button)
            }
        }

        viewModel.scissorHandBot.observe(this) {
            if (viewModel.scissorHandBot.value == HandType.SCISSOR.hand) {
                binding.ivGuntingOpponent.setBackgroundResource(R.drawable.bg_select_button)
            }
        }

        viewModel.paperHandBot.observe(this) {
            if (viewModel.paperHandBot.value == HandType.PAPER.hand) {
                binding.ivKertasOpponent.setBackgroundResource(R.drawable.bg_select_button)
            }
        }

        fun getSpannedStyle (result : String) : Spanned {
            return Html.fromHtml(result,FROM_HTML_MODE_LEGACY)
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
                else -> {
                    binding.tvVersus.text = ResultType.DEFAULT.result
                    binding.tvVersus.setTextColor(ContextCompat
                        .getColor(this,R.color.red_versus))
                }
            }
        }
    }

    private fun getRefreshViewModel(){
        viewModel.rockHandPlayer.value = null
        viewModel.scissorHandPlayer.value = null
        viewModel.paperHandPlayer.value = null
        viewModel.rockHandBot.value = null
        viewModel.scissorHandBot.value = null
        viewModel.paperHandBot.value = null
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