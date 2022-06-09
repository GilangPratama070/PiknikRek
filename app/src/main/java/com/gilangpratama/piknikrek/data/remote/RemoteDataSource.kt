package com.gilangpratama.piknikrek.data.remote

import com.gilangpratama.piknikrek.data.remote.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getListWisata() = apiService.getListWisata()

    suspend fun getDetailWisata(id: Int) = apiService.getDetailWisata(id)
}