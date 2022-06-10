package com.gilangpratama.piknikrek.data.repository.wisata

import androidx.lifecycle.LiveData
import com.gilangpratama.piknikrek.data.local.entity.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result

interface WisataRepository {
    fun getListWisata(): LiveData<Result<List<WisataEntity?>>>

    fun getDetailWisata(id: Int): LiveData<Result<List<WisataEntity?>>>

    fun searchWisata(query: String): LiveData<Result<List<WisataEntity?>>>
}