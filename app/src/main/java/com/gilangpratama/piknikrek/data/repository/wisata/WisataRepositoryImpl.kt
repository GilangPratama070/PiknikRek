package com.gilangpratama.piknikrek.data.repository.wisata

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gilangpratama.piknikrek.data.local.DetailEntity
import com.gilangpratama.piknikrek.data.local.WisataEntity
import com.gilangpratama.piknikrek.data.remote.RemoteDataSource
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.data.remote.response.wisata.Wisata
import javax.inject.Inject

class WisataRepositoryImpl @Inject constructor(private val remote: RemoteDataSource) : WisataRepository {

    override fun getListWisata(): LiveData<Result<List<WisataEntity?>>> =
        liveData {
            emit(Result.Loading)
            try {
                val result = remote.getListWisata()
                val list = result.data?.map {
                    WisataEntity(
                        it?.images,
                        it?.address,
                        it?.name,
                        it?.description,
                        it?.id,
                        it?.imageAvatar
                    )
                }
                emit(Result.Success(list))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    override fun getDetailWisata(id: Int): LiveData<Result<DetailEntity?>> =
        liveData {
            emit(Result.Loading)
            try {
                val result = remote.getDetailWisata(id)
                val detail = DetailEntity(
                    result.result?.images,
                    result.result?.address,
                    result.result?.name,
                    result.result?.description,
                    result.result?.id,
                    result.result?.imageAvatar
                )
                emit(Result.Success(detail))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }
}