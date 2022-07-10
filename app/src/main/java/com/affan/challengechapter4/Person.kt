package com.affan.challengechapter4

class Person : PlayerEngine() {
    override fun playerHand(): String {
        return HandType.A.nameHand
    }


}