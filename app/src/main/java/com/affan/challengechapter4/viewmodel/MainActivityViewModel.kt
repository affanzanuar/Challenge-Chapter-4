package com.affan.challengechapter4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var rockHandPlayer = MutableLiveData<String>()
    var scissorHandPlayer = MutableLiveData<String>()
    var paperHandPlayer = MutableLiveData<String>()
    var rockHandBot = MutableLiveData<String>()
    var scissorHandBot = MutableLiveData<String>()
    var paperHandBot = MutableLiveData<String>()
    var result = MutableLiveData<String>()

}