package com.bri.androidstudy.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message


    protected val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() = _progress


}