package com.catganisation.catalog.data.local.preferences

interface Persister {

    fun <T> set(key: Key, value: T?)

    fun <T> get(key: Key): T?

    fun has(key: Key): Boolean

    fun clear()

    enum class Key {

        User,
        SessionToken
    }
}