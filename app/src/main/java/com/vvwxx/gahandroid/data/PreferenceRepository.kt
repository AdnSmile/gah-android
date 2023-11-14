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
                it[LOGIN_KEY] ?: false,
                it[ROLE_KEY] ?: "",
                it[ID_CUSTOMER] ?: 0,
                it[HARGA_UPDATE] ?: 0,
                it[DEWASA] ?: 0,
                it[ANAK] ?: 0,
                it[CHECK_IN] ?: "",
                it[CHECK_OUT] ?: ""
            )
        }
    }

    suspend fun saveAccountPref(pref: Preference) {

        dataStore.edit {
            it[ID_KEY] = pref.id
            it[TOKEN_KEY] = pref.token
            it[LOGIN_KEY] = pref.isLogin
            it[ROLE_KEY] = pref.role
            it[ID_CUSTOMER] = pref.idCustomer
        }
    }

    suspend fun setHargaTerbaru(harga: Int) {

        dataStore.edit {
            it[HARGA_UPDATE] = harga
        }
    }

    suspend fun setReservasiPref(dewasa: Int, anak: Int, checkin: String, checkout: String) {

        dataStore.edit {
            it[DEWASA] = dewasa
            it[ANAK] = anak
            it[CHECK_IN] = checkin
            it[CHECK_OUT] = checkout
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
        private val ROLE_KEY = stringPreferencesKey("role")
        private val ID_CUSTOMER = intPreferencesKey("id_customer")
        private val HARGA_UPDATE = intPreferencesKey("harga_terbaru")
        private val DEWASA = intPreferencesKey("dewasa")
        private val ANAK = intPreferencesKey("anak")
        private val CHECK_IN = stringPreferencesKey("checkin")
        private val CHECK_OUT = stringPreferencesKey("checkout")

        fun getInstance(dataStore: DataStore<Preferences>): PreferenceRepository {

            return INSTANCE ?: synchronized(this) {
                val instance = PreferenceRepository(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}