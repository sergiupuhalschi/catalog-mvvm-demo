package com.catganisation.catalog.injection.modules

import com.catganisation.catalog.data.local.preferences.Persister
import com.catganisation.catalog.data.remote.services.CatsApi
import com.catganisation.catalog.data.repositories.CatsMainRepository
import com.catganisation.catalog.data.repositories.UserMainRepository
import com.catganisation.catalog.data.repositories.base.CatsRepository
import com.catganisation.catalog.data.repositories.base.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        persister: Persister
    ): UserRepository = UserMainRepository(persister)

    @Provides
    @Singleton
    fun provideCatsRepository(
        catsApi: CatsApi
    ): CatsRepository = CatsMainRepository(catsApi)
}