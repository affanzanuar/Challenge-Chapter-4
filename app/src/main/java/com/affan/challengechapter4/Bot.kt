package com.affan.challengechapter4

class Bot : PlayerEngine() {

    private val arrayHandType = arrayOf(
        HandType.A.nameHand,
        HandType.B.nameHand,
        HandType.C.nameHand
    )

    override fun playerHand(): String {
        return arrayHandType.random()
    }

}