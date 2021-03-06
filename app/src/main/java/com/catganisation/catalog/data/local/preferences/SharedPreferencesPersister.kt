package com.catganisation.catalog.data.local.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesPersister @Inject constructor(
    private val gson: Gson,
    private val preferences: SharedPreferences
) : Persister {

    override fun set(key: Persister.Key, value: String?) {
        value ?: return
        preferences.edit { putString(key.name, value) }
    }

    override fun get(key: Persister.Key): String? {
        return preferences.getString(key.name, null)
    }

    override fun <T> set(key: Persister.Key, value: T?) {
        value ?: return
        val stringValue = gson.toJson(value)
        preferences.edit { putString(key.name, stringValue) }
    }

    override fun <T> getObject(key: Persister.Key): T? {
        val stringValue = preferences.getString(key.name, null)
        stringValue ?: return null
        return gson.fromJson(stringValue, object : TypeToken<T>() {}.type)
    }

    override fun has(key: Persister.Key): Boolean {
        return preferences.contains(key.name)
    }

    override fun clear() {
        preferences.edit { clear() }
    }
}