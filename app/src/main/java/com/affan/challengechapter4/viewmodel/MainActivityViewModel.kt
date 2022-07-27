package com.affan.challengechapter4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _handPlayer = MutableLiveData<String>()
    val handPlayer : LiveData<String>
        get() =_handPlayer

    private val _handOpponent = MutableLiveData<String>()
    val handOpponent : LiveData<String>
        get() =_handOpponent

    private val _result = MutableLiveData<String>()
    val result : LiveData<String>
        get() =_result

    fun setHandPlayer (hand : String) {
        val current = handPlayer.value ?: hand
        _handPlayer.value = current
    }

    fun setHandOpponent (message : String) {
        val current = handOpponent.value ?: message
        _handOpponent.value = current
    }

    fun setResult (value : String) {
        val current = result.value ?: value
        _result.value = current
    }

    fun getRefreshViewModel(){
        _handPlayer.value = null
        _handOpponent.value = null
        _result.value = null
    }
}