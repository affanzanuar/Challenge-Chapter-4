package com.affan.challengechapter4.model

import android.util.Log
import com.affan.challengechapter4.utility.HandType
import com.affan.challengechapter4.utility.ResultType

abstract class PlayerEngine {

    open var playerHand : String = ""

    private var a = HandType.ROCK.hand
    private var b = HandType.PAPER.hand
    private var c = HandType.SCISSOR.hand

    private val tag = PlayerEngine::class.java.simpleName

    fun getAttack(opponent : PlayerEngine) : String {
        val playerHand = this.playerHand
        val opponentHand = opponent.playerHand

        fun logHand(){
            Log.d("myHand in PlayerEngine",playerHand)
            Log.d("botHand in PlayerEngine",opponentHand)
        }

        return if (playerHand==opponentHand){
            Log.d(tag,ResultType.DRAW.result)
            logHand()
            ResultType.DRAW.result
        } else if (
                playerHand==a && opponentHand==c ||
                playerHand==b && opponentHand==a ||
                playerHand==c && opponentHand==b) {
            Log.d(tag,ResultType.WIN.result)
            logHand()
            ResultType.WIN.result
        } else {
            Log.d(tag,ResultType.LOSE.result)
            logHand()
            ResultType.LOSE.result
        }
    }
}