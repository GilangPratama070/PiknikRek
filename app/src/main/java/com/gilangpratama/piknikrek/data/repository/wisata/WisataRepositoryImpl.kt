package com.gilangpratama.piknikrek.data.repository.wisata

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gilangpratama.piknikrek.data.local.entity.WisataEntity
import com.gilangpratama.piknikrek.data.remote.RemoteDataSource
import com.gilangpratama.piknikrek.data.remote.Result
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

    override fun getDetailWisata(id: Int): LiveData<Result<List<WisataEntity?>>> =
        liveData {
            emit(Result.Loading)
            try {
                val result = remote.getDetailWisata(id)
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

    override fun searchWisata(query: String): LiveData<Result<List<WisataEntity?>>> =
        liveData {
            emit(Result.Loading)
            try {
                val result = remote.searchWisata(query)
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
}