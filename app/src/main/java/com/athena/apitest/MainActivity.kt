package com.athena.apitest

import android.icu.text.MessagePattern.ApostropheMode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.athena.apitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var item = mutableListOf<ApiItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        item.add(ApiItem("api 가져오기"))
        item.add(ApiItem("api 가져오기"))
        item.add(ApiItem("api 가져오기"))

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = ApiAdapter(this,item)

        //recyclerView에 텍스트 가져오기
        //openAPI로 우선 가져와보기

    }
}