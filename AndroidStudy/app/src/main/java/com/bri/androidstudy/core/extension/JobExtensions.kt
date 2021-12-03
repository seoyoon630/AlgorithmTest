package com.bri.androidstudy.core.extension

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job


fun Job.progress(isProgress: MutableLiveData<Boolean>) {
    isProgress.postValue(true)
    invokeOnCompletion { isProgress.postValue(false) }
}
