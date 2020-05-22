package com.tatvasoft.nativepostkotlin.ui.post.datasource
import androidx.paging.PageKeyedDataSource
import com.tatvasoft.nativepostkotlin.interfaces.ApiService
import com.tatvasoft.nativepostkotlin.retrofit.ApiServiceBuilder
import com.tatvasoft.nativepostkotlin.ui.post.model.HitsItem
import com.tatvasoft.nativepostkotlin.ui.post.model.PostResponseModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class PostDataSource : PageKeyedDataSource<Int, HitsItem>() {
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, HitsItem>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getPosts(params.key)
        call.enqueue(object : Callback<PostResponseModel> {
            override fun onResponse(call: Call<PostResponseModel>, response: Response<PostResponseModel>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.hits
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<PostResponseModel>, t: Throwable) {
            }
        })
    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, HitsItem>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getPosts(FIRST_PAGE)
        call.enqueue(object : Callback<PostResponseModel> {
            override fun onResponse(call: Call<PostResponseModel>, response: Response<PostResponseModel>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.hits
                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }
            override fun onFailure(call: Call<PostResponseModel>, t: Throwable) {
            }
        })
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, HitsItem>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getPosts(params.key)
        call.enqueue(object : Callback<PostResponseModel> {
            override fun onResponse(call: Call<PostResponseModel>, response: Response<PostResponseModel>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.hits
                    val key = params.key + 1
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<PostResponseModel>, t: Throwable) {
            }
        })
    }
    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1
    }
}