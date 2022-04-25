package com.example.rickandmorty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityCharacterDetailsBinding

class CharacterDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCharacterDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_character_details)
        val intentFromMainActivity = intent
        Log.d("tester",intentFromMainActivity.getStringExtra("name").toString())
        binding.detailTitleTextView.text = intentFromMainActivity.getStringExtra("name")
        binding.detailDescriptionTextView.text = intentFromMainActivity.getStringExtra("species")
        binding.detailLocationTextView.text = intentFromMainActivity.getStringExtra("location")
        binding.detailTypeTextView.text = intentFromMainActivity.getStringExtra("type")
        binding.detailGenderTextView.text = intentFromMainActivity.getStringExtra("gender")
        binding.detailImageView.setImage(intentFromMainActivity.getStringExtra("image").toString())



    }
}