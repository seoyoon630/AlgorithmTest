package com.bri.androidstudy.data

import com.bri.androidstudy.domain.MainRepository
import com.bri.androidstudy.domain.model.UserResult

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override suspend fun getList(page: Int, query: String, perPage : Int): UserResult {
        return dataSource.getList(page, query, perPage)
    }
}