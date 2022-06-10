package com.gilangpratama.piknikrek.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gilangpratama.piknikrek.data.local.entity.WisataEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.data.repository.wisata.WisataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val wisataRepository: WisataRepository
): ViewModel() {

    fun searchWisata(query: String): LiveData<Result<List<WisataEntity?>>> =
        wisataRepository.searchWisata(query)
}