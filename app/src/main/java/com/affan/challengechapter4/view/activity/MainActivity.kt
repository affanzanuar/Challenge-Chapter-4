package com.affan.challengechapter4.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.affan.challengechapter4.*
import com.affan.challengechapter4.model.user.Bot
import com.affan.challengechapter4.model.user.Person
import com.affan.challengechapter4.data.HandType
import com.affan.challengechapter4.data.ResultType
import com.affan.challengechapter4.databinding.ActivityMainBinding
import com.affan.challengechapter4.model.user.PlayerWithParcelable
import com.affan.challengechapter4.view.fragment.CustomDialogFragment
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
        binding.tvPlayerName.text = getNamePlayer()
        getCategoryGame()
        getObserve()
    }

    private fun getNamePlayer(): String? {
        val personParcelable = intent
            .getParcelableExtra<PlayerWithParcelable>(MenuActivity.PLAYER_NAME_PRCLB)
                as PlayerWithParcelable
        return personParcelable.nama
    }
    private fun getCategoryGame (){
        gameCategory = intent.getIntExtra(MenuActivity.GAME_CATEGORY,0)
        if (gameCategory == 1){
            binding.tvPlayerKomputer.setText(R.string.pemain_2)
            getClickListenerPlayerOne()
            getClickListenerPlayerTwo()
            getShortToast("MultiPlayer dipilih")

        } else {
            getClickListenerPlayerOne()
            getShortToast("SinglePlayer dipilih")
        }
    }

    private fun getClickListenerPlayerOne() {
        gameCategory = intent.getIntExtra(MenuActivity.GAME_CATEGORY,0)
        binding.ivBatuPlayer.setOnClickListener {
            startGamePlayerOne(HandType.ROCK.hand)
            if (gameCategory == 2){
                getRandomHand()
                viewModel.setResult(playerOne.getAttack(playerBot))
            }
        }

        binding.ivGuntingPlayer.setOnClickListener {
            startGamePlayerOne(HandType.SCISSOR.hand)
            if (gameCategory == 2){
                getRandomHand()
                viewModel.setResult(playerOne.getAttack(playerBot))
            }
        }

        binding.ivKertasPlayer.setOnClickListener {
            startGamePlayerOne(HandType.PAPER.hand)
            if (gameCategory == 2){
                getRandomHand()
                viewModel.setResult(playerOne.getAttack(playerBot))
            }
        }

        binding.ivRefresh.setOnClickListener {
            getRefreshBackgroundPlayer()
            getRefreshBackgroundOpponent()
            playerOne.playerHand = ""
            playerTwo.playerHand = ""
            viewModel.getRefreshViewModel()
            binding.tvVersus.text = ResultType.DEFAULT.result
            viewModel.setResult(ResultType.DEFAULT.result)
        }
    }

    private fun getClickListenerPlayerTwo(){
        binding.ivBatuOpponent.setOnClickListener {
            if (playerOne.playerHand.isEmpty()){
                getLongToast("${getNamePlayer()} Pick First")
            } else {
                startGamePlayerTwo(HandType.ROCK.hand)
                viewModel.setResult(playerOne.getAttack(playerTwo))
            }
        }

        binding.ivGuntingOpponent.setOnClickListener {
            if (playerOne.playerHand.isEmpty()){
                getLongToast("${getNamePlayer()} Pick First")
            } else {
                startGamePlayerTwo(HandType.SCISSOR.hand)
                viewModel.setResult(playerOne.getAttack(playerTwo))
            }
        }

        binding.ivKertasOpponent.setOnClickListener {
            if (playerOne.playerHand.isEmpty()){
                getLongToast("${getNamePlayer()} Pick First")
            } else {
                startGamePlayerTwo(HandType.PAPER.hand)
                viewModel.setResult(playerOne.getAttack(playerTwo))
            }
        }
    }

    private fun startGamePlayerOne(hand : String){
        getRefreshBackgroundPlayer()
        viewModel.getRefreshViewModel()
        viewModel.setHandPlayer(hand)
        playerOne.playerHand = hand
    }

    private fun startGamePlayerTwo(hand : String){
        getRefreshBackgroundOpponent()
        viewModel.getRefreshViewModel()
        viewModel.setHandOpponent(hand)
        playerTwo.playerHand = hand
    }

    private fun getRandomHand (){
        getRefreshBackgroundOpponent()
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
            gameCategory = intent.getIntExtra(MenuActivity.GAME_CATEGORY,0)
            when (viewModel.result.value){
                ResultType.DRAW.result -> {
                    val dialogFragment = CustomDialogFragment("DRAW"," ")
                    dialogFragment.show(supportFragmentManager,null)
                }
                ResultType.WIN.result -> {
                    val dialogFragment = CustomDialogFragment(getNamePlayer()!!, "MENANG!")
                    dialogFragment.show(supportFragmentManager,null)
                }
                ResultType.LOSE.result -> {
                    if (gameCategory==1){
                        val dialogFragment = CustomDialogFragment("Pemain 2", "MENANG!")
                        dialogFragment.show(supportFragmentManager,null)
                    } else {
                        val dialogFragment = CustomDialogFragment("CPU", "MENANG!")
                        dialogFragment.show(supportFragmentManager,null)
                    }
                }
            }
        }
    }

    private fun getRefreshBackgroundPlayer(){
        binding.ivBatuPlayer.setBackgroundResource(0)
        binding.ivKertasPlayer.setBackgroundResource(0)
        binding.ivGuntingPlayer.setBackgroundResource(0)
    }

    private fun getRefreshBackgroundOpponent(){
        binding.ivBatuOpponent.setBackgroundResource(0)
        binding.ivKertasOpponent.setBackgroundResource(0)
        binding.ivGuntingOpponent.setBackgroundResource(0)
    }

    private fun getLongToast(message : String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    private fun getShortToast(message : String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}