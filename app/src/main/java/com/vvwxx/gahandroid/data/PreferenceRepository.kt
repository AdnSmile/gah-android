package com.vvwxx.gahandroid.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.vvwxx.gahandroid.data.model.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferenceRepository private constructor(private val dataStore: DataStore<Preferences>) {

    fun getAccountPref(): Flow<Preference> {
        return dataStore.data.map {
            Preference(
                it[ID_KEY] ?: 0,
                it[TOKEN_KEY] ?: "",
                it[LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun saveAccountPref(pref: Preference) {

        dataStore.edit {
            it[ID_KEY] = pref.id
            it[TOKEN_KEY] = pref.token
            it[LOGIN_KEY] = pref.isLogin
        }
    }

    suspend fun logout() {
        dataStore.edit {
            it.clear()
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: PreferenceRepository? = null

        private val ID_KEY = intPreferencesKey("id")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val LOGIN_KEY = booleanPreferencesKey("login")

        fun getInstance(dataStore: DataStore<Preferences>): PreferenceRepository {

            return INSTANCE ?: synchronized(this) {
                val instance = PreferenceRepository(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}