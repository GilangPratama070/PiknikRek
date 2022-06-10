package com.gilangpratama.piknikrek.data.repository.setting

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData

interface SettingRepository {

    fun isFirstTime(): LiveData<Boolean>

    suspend fun setIsFirstTime(isFirstTime: Boolean): Preferences
}