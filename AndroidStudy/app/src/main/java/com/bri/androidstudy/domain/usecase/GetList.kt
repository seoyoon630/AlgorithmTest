package com.bri.androidstudy.domain.usecase

import com.bri.androidstudy.domain.MainRepository
import com.bri.androidstudy.domain.model.UserResult

class GetList(private val mainRepository: MainRepository) {
    suspend operator fun invoke(page: Int, query: String, perPage : Int): UserResult {
        return mainRepository.getList(page, query, perPage)
    }
}