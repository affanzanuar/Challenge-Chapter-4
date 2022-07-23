package com.affan.challengechapter4.model.user

import com.affan.challengechapter4.data.HandType
import com.affan.challengechapter4.model.engine.PlayerEngine

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