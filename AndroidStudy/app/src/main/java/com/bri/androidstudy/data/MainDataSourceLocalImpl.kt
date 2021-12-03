package com.bri.androidstudy.data

import com.bri.androidstudy.domain.model.UserResult

class MainDataSourceLocalImpl : MainDataSource {
    override suspend fun getList(page: Int, query: String, perPage: Int): UserResult {
        TODO("Not yet implemented")
    }
}