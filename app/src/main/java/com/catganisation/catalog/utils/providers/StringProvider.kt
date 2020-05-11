package com.catganisation.catalog.utils.providers

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface StringProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg args: Any): String

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int): String

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg args: Any): String
}