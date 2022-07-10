package com.affan.challengechapter4

import android.util.Log

abstract class PlayerEngine  {

    open var score : Int = 0
    open var health : Double = 100.0
    abstract fun playerHand() : String

    var a = HandType.A.nameHand
    var b = HandType.B.nameHand
    var c = HandType.C.nameHand

    fun isAttackWin(opponent : PlayerEngine) : Boolean{
        val playerHand = this.playerHand()
        val opponentHand = opponent.playerHand()
        return playerHand==a && opponentHand==b ||
            playerHand==b && opponentHand==c ||
            playerHand==c && opponentHand==a
    }

    fun isAttackDraw(opponent : PlayerEngine) : Boolean{
        val playerHand = this.playerHand()
        val opponentHand = opponent.playerHand()
        return playerHand == opponentHand
    }


}