package com.affan.challengechapter4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.affan.challengechapter4.data.HandType

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

    fun setRockHandPlayer () {
        val current = handPlayer.value ?: HandType.ROCK.hand
        _handPlayer.postValue(current)
    }

    fun setScissorHandPlayer () {
        val current = handPlayer.value ?: HandType.SCISSOR.hand
        _handPlayer.postValue(current)
    }

    fun setPaperHandPlayer () {
        val current = handPlayer.value ?: HandType.PAPER.hand
        _handPlayer.postValue(current)
    }

    fun setRockHandOpponent () {
        val current = handOpponent.value ?: HandType.ROCK.hand
        _handOpponent.postValue(current)
    }

    fun setScissorHandOpponent () {
        val current = handOpponent.value ?: HandType.SCISSOR.hand
        _handOpponent.postValue(current)
    }

    fun setPaperHandOpponent () {
        val current = handOpponent.value ?: HandType.PAPER.hand
        _handOpponent.postValue(current)
    }

    fun setResult (value : String) {
        val current = result.value ?: value
        _result.postValue(current)
    }

    fun getRefreshViewModel(){
        _handPlayer.value = null
        _handOpponent.value = null
        _result.value = null
    }
}