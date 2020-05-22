package com.tatvasoft.nativepostkotlin.interfaces
import com.tatvasoft.nativepostkotlin.ui.post.model.HitsItem
import com.tatvasoft.nativepostkotlin.ui.post.model.PostResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/v1/search_by_date?tags=story&page=")
    fun getPosts(@Query("page") page: Int): Call<PostResponseModel>
}