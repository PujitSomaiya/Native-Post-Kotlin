package com.tatvasoft.nativepostkotlin.ui.post.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tatvasoft.nativepostkotlin.ui.post.datasource.PostDataSource
import com.tatvasoft.nativepostkotlin.ui.post.datasource.PostDataSourceFactory

class MainViewModel : ViewModel() {
    var postList: LiveData<PagedList<HitsItem>>
    private var liveDataSource: LiveData<PostDataSource>

    init {
        val itemDataSourceFactory = PostDataSourceFactory()
        liveDataSource = itemDataSourceFactory.postLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PostDataSource.PAGE_SIZE)
            .build()
        postList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}