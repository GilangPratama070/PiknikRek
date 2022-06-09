package com.gilangpratama.piknikrek.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gilangpratama.piknikrek.data.local.DetailEntity
import com.gilangpratama.piknikrek.data.remote.Result
import com.gilangpratama.piknikrek.data.repository.wisata.WisataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val wisataRepository: WisataRepository
): ViewModel() {

    fun getDetailWisata(id: Int): LiveData<Result<DetailEntity?>> = wisataRepository.getDetailWisata(id)
}