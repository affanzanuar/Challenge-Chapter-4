package com.affan.challengechapter4

class Bot : PlayerEngine() {

    override var playerHand : String = ""

    fun getRandomHand () : String{
        return arrayOf("batu", "gunting" , "kertas").random()
    }
}