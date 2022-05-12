package com.omolleapaza.peruapps.data.source.remote.service

import com.omolleapaza.peruapps.data.source.remote.response.BasePost
import retrofit2.Response
import retrofit2.http.*


interface RestService {

    /*
     * Getpost service
     */
    @GET("search_by_date")
    suspend fun getPost(
        @Query("page") page: Int,
        @Query("hitsPerPage") limitPage: Int = 20,
        @Query("query") query: String = "mobile"
    ): Response<BasePost>



}