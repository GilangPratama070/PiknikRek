package com.gilangpratama.piknikrek.data.local

import com.gilangpratama.piknikrek.data.local.datastore.AppPreference
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val pref: AppPreference) {

    fun isFirstTime() = pref.isFirstTime()

    suspend fun setIsFirstTime(isFirstTime: Boolean) = pref.setIsFirstTime(isFirstTime)
}