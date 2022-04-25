package com.example.rickandmorty.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.CharacterDetailsModel
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.viewmodel.MainActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnCharacterClickListener {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        button = findViewById(R.id.button)
        initRecyclerView()
        initViewModel()
        button.setOnClickListener {
            val buttonIntent=Intent(this,GroupByActivity::class.java)
            startActivity(buttonIntent)

        }



    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter

        }
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getListData().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun onCharacterClick(item: CharacterDetailsModel, position: Int) {
        val intentToCharacterDetailsActivity = Intent(this,CharacterDetailsActivity::class.java)
        intentToCharacterDetailsActivity.putExtra("name",item.characterName)
        intentToCharacterDetailsActivity.putExtra("species",item.characterSpecies)
        intentToCharacterDetailsActivity.putExtra("image",item.imageCharacter)
        intentToCharacterDetailsActivity.putExtra("location",item.characterLocation?.locationName)
        intentToCharacterDetailsActivity.putExtra("type",item.characterType)
        intentToCharacterDetailsActivity.putExtra("gender",item.characterGender)
        startActivity(intentToCharacterDetailsActivity)
    }

}