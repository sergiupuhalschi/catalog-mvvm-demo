package com.catganisation.catalog.injection.modules

import android.app.Application
import android.content.Context
import com.catganisation.catalog.data.local.preferences.Persister
import com.catganisation.catalog.data.local.preferences.SharedPreferencesPersister
import com.catganisation.catalog.utils.providers.StringMainProvider
import com.catganisation.catalog.utils.providers.StringProvider
import com.catganisation.catalog.utils.providers.schedulers.RuntimeSchedulerProvider
import com.catganisation.catalog.utils.providers.schedulers.SchedulerProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_sh_preferences"

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providePersister(
        context: Context,
        gson: Gson
    ): Persister {
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        return SharedPreferencesPersister(gson, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = RuntimeSchedulerProvider()

    @Provides
    @Singleton
    fun provideStringProvider(context: Context): StringProvider = StringMainProvider(context)
}