package com.example.rickandmorty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.CharacterDetailsModel
import com.example.rickandmorty.databinding.ActivityGroupByBinding
import com.example.rickandmorty.viewmodel.CharacterGroupingActivityViewModel

class GroupByActivity : AppCompatActivity() {
    lateinit var binding: ActivityGroupByBinding
    lateinit var characterGroupingActivityViewModel: CharacterGroupingActivityViewModel
    lateinit var characterAdapter: CharacterAdapter
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_group_by)
        progressBar = binding.progressBar
        val optionsToGroup= arrayOf("Group By Status- Alive","Group By Status- Unknown","Group By Status- Dead","Group By Species- Human","Group By Species- Alien")
        val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,optionsToGroup)
        binding.spinner2.adapter=arrayAdapter
        binding.spinner2.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2==0){
                    characterGroupingActivityViewModel.makeGroupCall(1)
                }else if (p2==1){
                    characterGroupingActivityViewModel.makeGroupCall(2)
                }else if(p2==2){
                    characterGroupingActivityViewModel.makeGroupCall(3)
                }else if(p2==3){
                    characterGroupingActivityViewModel.makeGroupCall(4)
                }
                else if (p2 == 4){
                    characterGroupingActivityViewModel.makeGroupCall(5)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                characterGroupingActivityViewModel.makeGroupCall(1)
            }

        }

        setUpViewModel()

        binding.viewModel = characterGroupingActivityViewModel
        binding.lifecycleOwner = this
        characterAdapter = CharacterAdapter(ArrayList())

        initViewModel()

        if(characterGroupingActivityViewModel.getLiveDataObserver().value == null){
            characterGroupingActivityViewModel.makeGroupCall(1)
        }
        binding.groupRecyclerView.adapter = characterAdapter
        binding.groupRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpViewModel() {
        characterGroupingActivityViewModel = ViewModelProvider(this).get(CharacterGroupingActivityViewModel::class.java)
    }

    private fun initViewModel() {
        characterGroupingActivityViewModel.getLiveDataObserver().observe(this, Observer {
            characterAdapter.updateCharacterDataList(it)
        })
        characterGroupingActivityViewModel.progressBar.observe(this, Observer {
            if (it == true)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        })
    }
}