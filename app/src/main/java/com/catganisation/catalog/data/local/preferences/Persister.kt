package com.catganisation.catalog.data.local.preferences

interface Persister {

    fun set(key: Key, value: String?)

    fun get(key: Key): String?

    fun <T> set(key: Key, value: T?)

    fun <T> getObject(key: Key): T?

    fun has(key: Key): Boolean

    fun clear()

    enum class Key {

        User,
        SessionToken
    }
}