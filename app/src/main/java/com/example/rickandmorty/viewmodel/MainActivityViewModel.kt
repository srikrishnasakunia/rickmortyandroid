package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.model.CharacterDetailsModel
import com.example.rickandmorty.network.RetroInstance
import com.example.rickandmorty.network.RetroService
import com.example.rickandmorty.data.remote.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
class MainActivityViewModel: ViewModel() {

    lateinit var retroService: RetroService

    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<CharacterDetailsModel>> {
        return Pager (config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(viewModelScope)
    }

}