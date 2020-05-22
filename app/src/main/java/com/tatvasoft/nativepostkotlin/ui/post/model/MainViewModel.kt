package com.tatvasoft.nativepostkotlin.ui.post.model
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tatvasoft.nativepostkotlin.ui.post.datasource.PostDataSource
import com.tatvasoft.nativepostkotlin.ui.post.datasource.PostDataSourceFactory

class MainViewModel : ViewModel() {
    var userPagedList: LiveData<PagedList<HitsItem>>
    private var liveDataSource: LiveData<PostDataSource>
    init {
        val itemDataSourceFactory = PostDataSourceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PostDataSource.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}