package com.astro.cf_user_rating.API
import com.google.gson.annotations.SerializedName


data class CF_data(
    val result: List<Result>,
    val status: String
)

data class Result(
    val contestId: Int,
    val contestName: String,
    val handle: String,
    val newRating: Int,
    val oldRating: Int,
    val rank: Int,
    val ratingUpdateTimeSeconds: Int
)