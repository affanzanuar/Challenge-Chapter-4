package com.affan.challengechapter4

import android.util.Log

abstract class PlayerEngine {

    open var score : Int = 0
    open var health : Double = 100.0
    open var playerHand : String = ""

    private var a = HandType.A.hand
    private var b = HandType.B.hand
    private var c = HandType.C.hand

    fun attack(opponent : PlayerEngine) : String {
        val playerHand = this.playerHand
        val opponentHand = opponent.playerHand

        fun logHand(){
            Log.e("myHand in PlayerEngine",playerHand)
            Log.e("botHand in PlayerEngine",opponentHand)
        }

        return if (playerHand==opponentHand){
            Log.d("RESULT","draw")
            logHand()
            "draw"
        } else if (
                playerHand==a && opponentHand==c ||
                playerHand==b && opponentHand==a ||
                playerHand==c && opponentHand==b) {
            this.score++
            Log.d("RESULT","win")
            logHand()
            "win"
        } else {
            opponent.score++
            Log.d("RESULT","lose")
            logHand()
            "lose"
        }
    }
}