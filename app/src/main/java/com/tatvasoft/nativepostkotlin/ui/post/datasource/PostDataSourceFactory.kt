package com.tatvasoft.nativepostkotlin.ui.post.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tatvasoft.nativepostkotlin.ui.post.model.HitsItem

class PostDataSourceFactory : DataSource.Factory<Int, HitsItem>() {
    val postLiveDataSource = MutableLiveData<PostDataSource>()
    override fun create(): DataSource<Int, HitsItem> {
        val postDataSource = PostDataSource()
        postLiveDataSource.postValue(postDataSource)
        return postDataSource
    }
}