package com.affan.challengechapter4.model

import com.affan.challengechapter4.data.HandType

class Bot : PlayerEngine() {

    override var playerHand : String = ""

    fun getRandomHandBot () : String{
        return arrayOf(
            HandType.ROCK.hand,
            HandType.SCISSOR.hand,
            HandType.PAPER.hand
        ).random()
    }
}