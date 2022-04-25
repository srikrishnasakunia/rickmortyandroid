package com.example.rickandmorty.network

import com.example.rickandmorty.data.model.ApiResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {


    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query: Int):ApiResultModel

    @GET("character/?status=alive")
    suspend fun getAliveStatus():Response<ApiResultModel>

    @GET("character/?status=dead")
    suspend fun getDeadStatus():Response<ApiResultModel>

    @GET("character/?status=unknown")
    suspend fun getUnknownStatus():Response<ApiResultModel>

    @GET("character/?species=human")
    suspend fun getHumanSpecies():Response<ApiResultModel>

    @GET("character/?species=alien")
    suspend fun getAlienSpecies():Response<ApiResultModel>
}