package com.gilangpratama.piknikrek.data.repository.wisata

import androidx.lifecycle.LiveData
import com.gilangpratama.piknikrek.data.local.DetailEntity
import com.gilangpratama.piknikrek.data.local.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.data.remote.response.wisata.Wisata

interface WisataRepository {
    fun getListWisata(): LiveData<Result<List<WisataEntity?>>>

    fun getDetailWisata(id: Int): LiveData<Result<List<WisataEntity?>>>
}