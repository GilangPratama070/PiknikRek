package com.gilangpratama.piknikrek.data.remote.api

import com.gilangpratama.piknikrek.data.remote.response.detail.DetailResponse
import com.gilangpratama.piknikrek.data.remote.response.wisata.WisataResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @GET("wisata")
    suspend fun getListWisata(
        @Query("lat") lat: Int = 1,
        @Query("long") long: Int = 1
    ): WisataResponse

    @GET("detail-wisata")
    suspend fun getDetailWisata(
        @Query("id") id: Int
    ): DetailResponse

    @Multipart
    @POST("predict")
    suspend fun predictImg(
        @Part("image") image: MultipartBody.Part
    )
}