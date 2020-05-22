package com.tatvasoft.nativepostkotlin.ui.post.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tatvasoft.nativepostkotlin.ui.post.model.HitsItem

class PostDataSourceFactory : DataSource.Factory<Int, HitsItem>() {
    val userLiveDataSource = MutableLiveData<PostDataSource>()
    override fun create(): DataSource<Int, HitsItem> {
        val userDataSource = PostDataSource()
        userLiveDataSource.postValue(userDataSource)
        return userDataSource
    }
}