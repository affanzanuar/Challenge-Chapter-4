package com.affan.challengechapter4

class Bot : PlayerEngine() {

    val arrayHandType = arrayOf(
        HandType.A.nameHand,
        HandType.B.nameHand,
        HandType.C.nameHand
    ).random()

    override var playerHand: String = ""
//        get() = PlayActivity.ksrkd

    override fun playerHand () : String {
        return arrayHandType
    }

//    init {
//        super.playerHand = this.playerHand
//    }

}