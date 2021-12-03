package com.bri.androidstudy.presentation.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bri.androidstudy.core.extension.progress
import com.bri.androidstudy.core.platform.BaseViewModel
import com.bri.androidstudy.core.servicelocator.ServiceLocator
import com.bri.androidstudy.domain.model.User
import com.bri.androidstudy.domain.usecase.GetList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainViewModel : BaseViewModel() {
    private val perPage = 30
    private var page = 0
    var query = ""
    private var canSearchMore = true
    private val getList: GetList = ServiceLocator.provideGetList()

    private val _users = MutableLiveData<List<User>>()
    val addUsers: LiveData<List<User>> get() = _users

    private val _setUsers = MutableLiveData<List<User>>()
    val setUsers: LiveData<List<User>> get() = _setUsers

    private val _bitmaps = MutableLiveData<List<Pair<Int, Bitmap?>>>()
    val bitmaps: LiveData<List<Pair<Int, Bitmap?>>> get() = _bitmaps

    private val bitmapMap = HashMap<Int, Bitmap>()

    fun setUsers() {
        viewModelScope.launch {
            runCatching {
                if (query.trim().isEmpty()) {
                    _message.value = "검색어를 입력해주세요."
                    return@launch
                }
                page = 1
                getList(page, query, perPage).apply {
                    canSearchMore = page * perPage < total_count
                    Log.w("users", "" + users.size)
                    _setUsers.value = users
                    createBitmaps(users)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }.progress(_progress)
    }

    fun getNextUsers() {
        viewModelScope.launch {
            runCatching {
                if (!canSearchMore) {
                    _message.value = "마지막 페이지입니다."
                    return@launch
                }
                getList(++page, query, perPage).apply {
                    canSearchMore = page * perPage < total_count
                    Log.w("users", "" + users.size)
                    _users.value = users
                    createBitmaps(users)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }.progress(_progress)
    }

    private fun createBitmaps(users: List<User>) {
        viewModelScope.launch {
            runCatching {
                val queueSize = 5
                repeat(users.size / queueSize + (users.size % queueSize).coerceAtMost(1)) {
                    val bitmaps =
                        withContext(Dispatchers.IO) {
                            users
                                .filterIndexed { index, _ -> index in (it * queueSize..(it + 1) * queueSize) }
                                .map { user ->
                                    if (!bitmapMap.containsKey(user.id)) {
                                        val bitmap =
                                            BitmapFactory.decodeStream(URL(user.avatar_url).openStream())
                                        bitmapMap[user.id] = bitmap
                                    }
                                    Pair(user.id, bitmapMap[user.id])
                                }
                        }
                    _bitmaps.value = bitmaps
                }

            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}