package com.bri.androidstudy.domain.model

import com.google.gson.annotations.SerializedName

data class UserResult(
    val incomplete_results: Boolean,
    @SerializedName("items") val users: List<User>,
    val total_count: Int
)
