package com.gilangpratama.piknikrek.data.repository.setting

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.gilangpratama.piknikrek.data.local.LocalDataSource
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : SettingRepository {

    override fun isFirstTime(): LiveData<Boolean> =
        local.isFirstTime().asLiveData()

    override suspend fun setIsFirstTime(isFirstTime: Boolean): Preferences =
        local.setIsFirstTime(isFirstTime)
}