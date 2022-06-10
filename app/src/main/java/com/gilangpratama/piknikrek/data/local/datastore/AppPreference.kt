package com.gilangpratama.piknikrek.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPreference @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val FIRST_TIME_KEY = booleanPreferencesKey("first_time")

    fun isFirstTime(): Flow<Boolean> =
        dataStore.data.map {
            it[FIRST_TIME_KEY] ?: true
        }

    suspend fun setIsFirstTime(isFirst: Boolean) =
        dataStore.edit {
            it[FIRST_TIME_KEY] = isFirst
        }
}