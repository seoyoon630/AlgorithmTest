package com.bri.androidstudy.domain

import com.bri.androidstudy.domain.model.UserResult
import org.json.JSONObject

interface MainRepository {
    suspend fun getList(page: Int, query: String, perPage : Int): UserResult
}
