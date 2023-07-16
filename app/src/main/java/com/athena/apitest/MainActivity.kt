package com.athena.apitest

import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.apitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var item = ArrayList<DETAIL>()

    lateinit var viewModel: ViewModel
    private var adapter = ApiAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.refresh()

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = ApiAdapter(this,item)

        observeViewModel()
        return binding.root

        //recyclerView에 텍스트 가져오기
        //openAPI로 우선 가져와보기

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun observeViewModel() {
        viewModel.data.observe(owner, Observer {
            it?.let {
                adapter.update(it)
                adapter.notifyDataSetChanged()
            }
        })



    }//observeViewModel()


}