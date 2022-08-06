package com.affan.challengechapter4.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.affan.challengechapter4.*
import com.affan.challengechapter4.model.user.Bot
import com.affan.challengechapter4.model.user.Person
import com.affan.challengechapter4.data.HandType
import com.affan.challengechapter4.data.ResultType
import com.affan.challengechapter4.databinding.ActivityMainBinding
import com.affan.challengechapter4.databinding.CustomToastBinding
import com.affan.challengechapter4.model.user.PlayerWithParcelable
import com.affan.challengechapter4.view.fragment.CustomDialogFragment
import com.affan.challengechapter4.viewmodel.MainActivityViewModel

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), CustomDialogFragment.DialogListener {
    private lateinit var binding : ActivityMainBinding
    private var playerOne : Person = Person ()
    private var playerTwo : Person = Person ()
    private var playerBot : Bot = Bot ()
    private val viewModel : MainActivityViewModel by viewModels()
    private var gameCategory : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvPlayerName.text = getNamePlayer()
        getChooseOpponent()
        getObserve()
    }

    // Receive data player name with Parcelable from MenuActivity
    private fun getNamePlayer(): String {
        val personParcelable = intent
            .getParcelableExtra<PlayerWithParcelable>(MenuActivity.EXTRA_NAME_PARCELABLE)
                as PlayerWithParcelable
        return personParcelable.playerName!!
    }

    /**
    Receive data game category single or multiplayer with Bundle from MenuActivity.
    If true, user will choose multi player and vice versa
     **/
    private fun getGameCategory(): Boolean {
        return intent.getBooleanExtra(MenuActivity.EXTRA_CATEGORY,true)
    }

    private fun getChooseOpponent (){
        gameCategory = getGameCategory()
        if (gameCategory){
            binding.tvPlayerKomputer.setText(R.string.pemain_2)
            getClickListenerPlayerOne()
            getClickListenerPlayerTwo()
        } else {
            getClickListenerPlayerOne()
        }
    }

    private fun getClickListenerPlayerOne() {
        binding.ivBatuPlayer.setOnClickListener {
            startGamePlayerOne(HandType.ROCK.hand)
        }

        binding.ivGuntingPlayer.setOnClickListener {
            startGamePlayerOne(HandType.SCISSOR.hand)
        }

        binding.ivKertasPlayer.setOnClickListener {
            startGamePlayerOne(HandType.PAPER.hand)
        }

        binding.ivRefresh.setOnClickListener {
            getRefreshGame()
        }

        binding.ivClose?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getClickListenerPlayerTwo(){
        binding.ivBatuOpponent.setOnClickListener {
            startGamePlayerTwo(HandType.ROCK.hand)
        }

        binding.ivGuntingOpponent.setOnClickListener {
            startGamePlayerTwo(HandType.SCISSOR.hand)
        }

        binding.ivKertasOpponent.setOnClickListener {
            startGamePlayerTwo(HandType.PAPER.hand)
        }
    }

    private fun startGamePlayerOne(hand : String){
        gameCategory = getGameCategory()
        getRefreshBackgroundPlayer()
        viewModel.getRefreshViewModel()
        viewModel.setHandPlayer(hand)
        setCustomToast(getNamePlayer(),getString(R.string.choose_toast),hand)
        playerOne.playerHand = hand
        if (!gameCategory){
            getRandomHand()
            viewModel.setResult(playerOne.getAttack(playerBot))
        }
    }

    // We make player one play first, to match the flowchart
    private fun startGamePlayerTwo(hand : String){
        if (playerOne.playerHand.isEmpty()){
            setCustomToast(getNamePlayer(),getString(R.string.pick),"")
        } else {
            getRefreshBackgroundOpponent()
            viewModel.getRefreshViewModel()
            viewModel.setHandOpponent(hand)
            playerTwo.playerHand = hand
            setCustomToast(getString(R.string.pemain_2), getString(R.string.choose_toast), hand)
            viewModel.setResult(playerOne.getAttack(playerTwo))
        }
    }

    private fun getRandomHand (){
        getRefreshBackgroundOpponent()
        playerBot.getRandomHandBot().also {
            playerBot.playerHand = it
            setCustomToast(getString(R.string.cpu), getString(R.string.choose_toast), it)
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
            gameCategory = getGameCategory()
            when (viewModel.result.value){
                ResultType.DRAW.result -> {
                    setDialog(getString(R.string.seri),"")
                }
                ResultType.WIN.result -> {
                    setDialog(getNamePlayer(), getString(R.string.winner))
                }
                ResultType.LOSE.result -> {
                    if (gameCategory){
                        setDialog(getString(R.string.pemain_2), getString(R.string.winner))
                    } else {
                        setDialog(getString(R.string.cpu), getString(R.string.winner))
                    }
                }
            }
        }
    }

    private fun setDialog(name : String , result : String){
        val dialogFragment = CustomDialogFragment(name, result)
        dialogFragment.show(supportFragmentManager,null)
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

    private fun getRefreshGame() {
        getRefreshBackgroundPlayer()
        getRefreshBackgroundOpponent()
        playerOne.playerHand = ""
        playerTwo.playerHand = ""
        viewModel.getRefreshViewModel()
        binding.tvVersus.text = ResultType.DEFAULT.result
        viewModel.setResult(ResultType.DEFAULT.result)
    }

    private fun setCustomToast(user : String , choose : String , hand : String){
        val layout = CustomToastBinding.inflate(layoutInflater).root
        val textChoose = "$user $choose $hand"
        layout.findViewById<TextView>(R.id.tvChoose).text = textChoose
        Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    override fun getCloseDialog() {
        getRefreshGame()
    }

    override fun goToMenu() {
        onBackPressed()
    }
}