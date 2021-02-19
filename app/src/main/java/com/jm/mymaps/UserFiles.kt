package com.jm.mymaps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jm.mymaps.adapter.UserAdapter

class UserFiles : AppCompatActivity() {

    private lateinit var userAdapter : UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_files)

        initRecyclerView()
    }

    private fun addDataSet(){

    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_View)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserFiles)
            userAdapter = UserAdapter()
            adapter = userAdapter
        }
    }
}