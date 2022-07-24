package com.affan.challengechapter4.model.engine

import android.util.Log
import com.affan.challengechapter4.data.HandType
import com.affan.challengechapter4.data.ResultType

abstract class PlayerEngine {
    open var playerHand : String = ""
    private val tag = PlayerEngine::class.java.simpleName
    private var a = HandType.ROCK.hand
    private var b = HandType.PAPER.hand
    private var c = HandType.SCISSOR.hand

    fun getAttack(opponent : PlayerEngine) : String {
        val playerHand = this.playerHand
        val opponentHand = opponent.playerHand

        fun logHand(){
            Log.d("$tag : PLAYER 1 pick ",playerHand)
            Log.d("$tag : COMPUTER pick ",opponentHand)
        }

        return if (playerHand==opponentHand){
            logHand()
            Log.d("$tag : RESULT ",ResultType.DRAW.result)
            ResultType.DRAW.result
        } else if (
                playerHand==a && opponentHand==c ||
                playerHand==b && opponentHand==a ||
                playerHand==c && opponentHand==b) {
            logHand()
            Log.d("$tag : PLAYER 1 ",ResultType.WIN.result)
            ResultType.WIN.result
        } else {
            logHand()
            Log.d("$tag : COMPUTER ",ResultType.WIN.result)
            ResultType.LOSE.result
        }
    }
}