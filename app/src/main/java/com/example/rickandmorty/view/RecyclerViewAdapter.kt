package com.example.rickandmorty.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.CharacterDetailsModel

class RecyclerViewAdapter(var clickListener:OnCharacterClickListener): PagingDataAdapter<CharacterDetailsModel, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.character_list, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

       val imageView: ImageView = view.findViewById(R.id.display_ImageView)
        val tvName: TextView = view.findViewById(R.id.header_TextView)
        val tvDesc: TextView = view.findViewById(R.id.description_textView)
        val view:View = view
        val options = RequestOptions().error(R.drawable.error_image).placeholder(R.drawable.downloading)

        fun bind(data: CharacterDetailsModel,action:OnCharacterClickListener) {
            tvName.text = data.characterName
            tvDesc.text = data.characterSpecies

            Glide.with(imageView)
                .load(data.imageCharacter)
                .apply(options)
                .circleCrop()
                .into(imageView)

            view.setOnClickListener{
                action.onCharacterClick(data,absoluteAdapterPosition)
            }

        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterDetailsModel>() {
        override fun areItemsTheSame(oldItem: CharacterDetailsModel, newItem: CharacterDetailsModel): Boolean {
           return oldItem.characterName == newItem.characterName
        }

        override fun areContentsTheSame(oldItem: CharacterDetailsModel, newItem: CharacterDetailsModel): Boolean {
            return oldItem.characterName == newItem.characterName
                    && oldItem.characterSpecies == newItem.characterSpecies
        }

    }
interface OnCharacterClickListener{
    fun onCharacterClick(item:CharacterDetailsModel,position: Int)
}
}