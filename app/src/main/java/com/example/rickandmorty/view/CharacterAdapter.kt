package com.example.rickandmorty.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.CharacterDetailsModel
import com.example.rickandmorty.databinding.CharacterListBinding

class CharacterAdapter(
    val characterDataList:ArrayList<CharacterDetailsModel>): RecyclerView.Adapter<CharacterAdapter.characterViewHolder>() {
    inner class characterViewHolder(val binding: CharacterListBinding):
        RecyclerView.ViewHolder(binding.root){
        fun dataBinding(characterData:CharacterDetailsModel){
            binding.characterDetailsData = characterData
        }
    }
    fun updateCharacterDataList(characterData:List<CharacterDetailsModel>){
        characterDataList.clear()
        characterDataList.addAll(characterData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): characterViewHolder {
        var view = LayoutInflater.from(parent.context)
        val binding = CharacterListBinding.inflate(view,parent,false)
        return characterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: characterViewHolder, position: Int) {
        //holder.dataBinding(characterDataList[position])
        holder.dataBinding(characterDataList.get(position))
    }

    override fun getItemCount(): Int {
        return characterDataList.size
    }

}