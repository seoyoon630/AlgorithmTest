package com.programmers.kmooc.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.programmers.kmooc.activities.list.LecturesAdapter
import com.programmers.kmooc.models.Lecture
import com.programmers.kmooc.models.LectureList
import com.programmers.kmooc.repositories.KmoocRepository

class KmoocListViewModel(private val repository: KmoocRepository) : ViewModel() {

    private var prevLectureList: LectureList? = null

    val adapter = LecturesAdapter()
    private val mLectures = ArrayList<Lecture>()
    private val _lectures = MutableLiveData<List<Lecture>>(listOf())
    val lectures: LiveData<List<Lecture>> get() = _lectures

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean> get() = _progress

    fun list() {
        _progress.value = true
        repository.list { lectureList ->
            prevLectureList = lectureList
            mLectures.clear()
            mLectures.addAll(lectureList.lectures)
            Log.w("lectures", "${mLectures.size}")
            Log.w("lectures", mLectures.joinToString { it.name })
            _lectures.postValue(mLectures)
            _progress.postValue(false)
        }
    }

    fun next() {
        _progress.value = true
        prevLectureList?.let {
            repository.next(it) { lectureList ->
                prevLectureList = lectureList
                mLectures.addAll(lectureList.lectures)
                Log.w("lectures", "${mLectures.size}")
                Log.w("lectures", mLectures.joinToString { it.name })
                _lectures.postValue(mLectures)
                _progress.postValue(false)
            }
        } ?: run { list() }
    }
}

class KmoocListViewModelFactory(private val repository: KmoocRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KmoocListViewModel::class.java)) {
            return KmoocListViewModel(repository) as T
        }
        throw IllegalAccessException("Unkown Viewmodel Class")
    }
}