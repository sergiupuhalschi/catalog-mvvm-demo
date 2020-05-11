package com.catganisation.catalog.utils.providers

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import javax.inject.Inject

class StringMainProvider @Inject constructor(
    private val context: Context
) : StringProvider {

    override fun getString(@StringRes resId: Int): String = context.getString(resId)

    override fun getString(
        @StringRes resId: Int,
        vararg args: Any
    ): String = context.getString(resId, *args)

    override fun getQuantityString(
        @PluralsRes resId: Int,
        quantity: Int
    ): String = context.resources.getQuantityString(resId, quantity)

    override fun getQuantityString(
        @PluralsRes resId: Int,
        quantity: Int,
        vararg args: Any
    ): String = context.resources.getQuantityString(resId, quantity, *args)
}