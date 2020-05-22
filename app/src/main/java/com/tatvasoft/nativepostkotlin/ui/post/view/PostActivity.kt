package com.tatvasoft.nativepostkotlin.ui.post.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tatvasoft.nativepostkotlin.R
import com.tatvasoft.nativepostkotlin.interfaces.RecyclerInterface
import com.tatvasoft.nativepostkotlin.ui.post.adapter.PostAdapter
import com.tatvasoft.nativepostkotlin.ui.post.model.MainViewModel
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class PostActivity : AppCompatActivity(), LifecycleOwner, RecyclerInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        val adapter = PostAdapter(this)
        val tvCheckNumber = tvCheckNumber
        tvCheckNumber.findViewById<TextView>(R.id.tvCheckNumber)
        recyclerPosts.layoutManager = LinearLayoutManager(this)
        val itemViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        fetchData(this, itemViewModel, adapter, recyclerPosts)
        swipeToRefresh.setOnRefreshListener {
            fetchData(this, itemViewModel, adapter, recyclerPosts)
            swipeToRefresh.isRefreshing = false
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onItemClick(number: Int) {
        if (number != 0) {
            tvCheckNumber?.text = "Selected: $number"
        } else {
            tvCheckNumber?.text = ""
        }
    }
}

private fun fetchData(
    context: LifecycleOwner,
    itemViewModel: MainViewModel,
    adapter: PostAdapter,
    recyclerPosts: RecyclerView
) {
    itemViewModel.userPagedList.observe(context, Observer {
        adapter.submitList(it)
    })
    recyclerPosts.adapter = adapter
}


