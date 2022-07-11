package com.affan.challengechapter4

import android.util.Log

abstract class PlayerEngine {

    open var score : Int = 0
    open var health : Double = 100.0
    open var playerHand : String = ""
    abstract fun playerHand () : String

    var a = HandType.A.nameHand
    var b = HandType.B.nameHand
    var c = HandType.C.nameHand

    fun attack(opponent : PlayerEngine) : String {
        val playerHand = this.playerHand
        val opponentHand = opponent.playerHand
        return if (playerHand==opponentHand){
            Log.e("myHand in PlayerEngine",playerHand)
            Log.e("handBot in PlayerEngine",opponentHand)
            Log.d("BINAR","draw")
            "draw"
        } else if (
                playerHand==a && opponentHand==c ||
                playerHand==b && opponentHand==a ||
                playerHand==c && opponentHand==b) {
            this.score++
            Log.e("myHand in PlayerEngine",playerHand)
            Log.e("handBot in PlayerEngine",opponentHand)
            Log.d("BINAR","win")
            "win"
        } else {
            opponent.score++
            Log.e("myHand in PlayerEngine",playerHand)
            Log.e("handBot in PlayerEngine",opponentHand)
            Log.d("BINAR","lose")
            "lose"
        }
    }
}