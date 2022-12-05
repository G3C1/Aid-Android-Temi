package com.g3c1.oasis_android_temi.presentation.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SearialNumberManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "searialNumberDataStore")

    private val key = intPreferencesKey("searialNumber")

    val searialNumber: Flow<Int> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[key] ?: 0
        }

    suspend fun setSearialNumber(text: Int) {
        context.dataStore.edit { preferences ->
            preferences[key] = text
        }
    }
}