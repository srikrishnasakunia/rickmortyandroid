package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName

data class ApiResultModel(
    @SerializedName("info") val info: InfoModel,
    @SerializedName("results") val results: List<CharacterDetailsModel>
)