package com.affan.challengechapter4.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import com.affan.challengechapter4.*
import com.affan.challengechapter4.model.user.Bot
import com.affan.challengechapter4.model.user.Person
import com.affan.challengechapter4.data.HandType
import com.affan.challengechapter4.data.ResultType
import com.affan.challengechapter4.databinding.ActivityMainBinding
import com.affan.challengechapter4.model.user.PlayerWithParcelable
import com.affan.challengechapter4.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var playerOne : Person = Person ()
    private var playerTwo : Person = Person ()
    private var playerBot : Bot = Bot ()
    private val viewModel : MainActivityViewModel by viewModels()
    private var gameCategory : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personParcelable = intent
            .getParcelableExtra<PlayerWithParcelable>(MenuActivity.PLAYER_NAME_PRCLB)
                as PlayerWithParcelable
        val namePlayer = personParcelable.nama
        binding.tvPlayerName.text = namePlayer

        getCategoryGame()
        getObserve()
    }

    private fun getCategoryGame (){
        gameCategory = intent.getIntExtra(MenuActivity.GAME_CATEGORY,0)
        if (gameCategory == 1){
            binding.tvPlayerKomputer.setText(R.string.pemain_2)
            getClickListenerPlayerOne()
            getClickListenerPlayerTwo()
            getToastLong("MultiPlayer dipilih")
        } else {
            getClickListenerPlayerOne()
            getToastLong("SinglePlayer dipilih")
        }
    }

    private fun getClickListenerPlayerOne() {
        binding.ivBatuPlayer.setOnClickListener {
            startGame(HandType.ROCK.hand)
            if (gameCategory == 2){
                getRandomHand()
                viewModel.setResult(playerOne.getAttack(playerBot))
            }
        }

        binding.ivGuntingPlayer.setOnClickListener {
            startGame(HandType.SCISSOR.hand)
            if (gameCategory == 2){
                getRandomHand()
                viewModel.setResult(playerOne.getAttack(playerBot))
            }
        }

        binding.ivKertasPlayer.setOnClickListener {
            startGame(HandType.PAPER.hand)
            if (gameCategory == 2){
                getRandomHand()
                viewModel.setResult(playerOne.getAttack(playerBot))
            }
        }

        binding.ivRefresh.setOnClickListener {
            getRefreshBackground()
            viewModel.getRefreshViewModel()
            binding.tvVersus.text = ResultType.DEFAULT.result
            viewModel.setResult(ResultType.DEFAULT.result)
        }
    }

    private fun getClickListenerPlayerTwo(){
        binding.ivBatuOpponent.setOnClickListener {
            startGameTwo(HandType.ROCK.hand)
            viewModel.setResult(playerOne.getAttack(playerTwo))
        }

        binding.ivGuntingOpponent.setOnClickListener {
            startGameTwo(HandType.SCISSOR.hand)
            viewModel.setResult(playerOne.getAttack(playerTwo))
        }

        binding.ivKertasOpponent.setOnClickListener {
            startGameTwo(HandType.PAPER.hand)
            viewModel.setResult(playerOne.getAttack(playerTwo))
        }
    }

    private fun startGame(hand : String){
        getRefreshBackground()
        viewModel.getRefreshViewModel()
        viewModel.setHandPlayer(hand)
        playerOne.playerHand = hand
    }

    private fun startGameTwo(hand : String){
        getRefreshBackground()
        viewModel.getRefreshViewModel()
        viewModel.setHandOpponent(hand)
        playerTwo.playerHand = hand
    }

    private fun getRandomHand (){
        playerBot.getRandomHandBot().also {
            playerBot.playerHand = it
        }
        when (playerBot.playerHand){
            HandType.ROCK.hand -> viewModel.setHandOpponent(HandType.ROCK.hand)
            HandType.SCISSOR.hand -> viewModel.setHandOpponent(HandType.SCISSOR.hand)
            HandType.PAPER.hand -> viewModel.setHandOpponent(HandType.PAPER.hand)
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

    private fun getToastLong(message : String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}