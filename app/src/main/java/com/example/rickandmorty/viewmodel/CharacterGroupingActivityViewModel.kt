package com.example.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.model.CharacterDetailsModel
import com.example.rickandmorty.network.RetroInstance
import com.example.rickandmorty.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CharacterGroupingActivityViewModel:ViewModel() {
    private var liveCharacterDataList:MutableLiveData<List<CharacterDetailsModel>>
    var progressBar:MutableLiveData<Boolean> = MutableLiveData()

    init {
        liveCharacterDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): LiveData<List<CharacterDetailsModel>> {
        return liveCharacterDataList
    }

    fun makeGroupCall(case:Int){
        progressBar.value=true
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            try {
                when(case){
                    1 -> {
                        val response = retroInstance.getAliveStatus().body()
                        val listResponse = response?.results
                        Log.d("GroupByAlive",listResponse.toString())
                        liveCharacterDataList.postValue(listResponse)
                    }
                    2 -> {
                        val response = retroInstance.getDeadStatus().body()
                        val listResponse = response?.results
                        Log.d("GroupByDead",listResponse.toString())
                        liveCharacterDataList.postValue(listResponse)
                    }
                    3 -> {
                        val response = retroInstance.getUnknownStatus().body()
                        val listResponse = response?.results
                        Log.d("GroupByUnknown",listResponse.toString())
                        liveCharacterDataList.postValue(listResponse)
                    }
                    4 -> {
                        val response = retroInstance.getHumanSpecies().body()
                        val listResponse = response?.results
                        Log.d("GroupByHuman",listResponse.toString())
                        liveCharacterDataList.postValue(listResponse)
                    }
                    5 -> {
                        val response = retroInstance.getAlienSpecies().body()
                        val listResponse = response?.results
                        Log.d("GroupByAlien",listResponse.toString())
                        liveCharacterDataList.postValue(listResponse)
                    }
            }
        }
            catch (e: IOException){
                Log.d("IOException","IOException")
            }catch (e: HttpException){
                Log.d("HttpException","HTTPException")
            }
            progressBar.postValue(false)
        }
    }

}