package com.affan.challengechapter4.model

class Bot : PlayerEngine() {

    override var playerHand : String = ""

    fun getRandomHand () : String{
        return arrayOf("batu", "gunting" , "kertas").random()
    }
}