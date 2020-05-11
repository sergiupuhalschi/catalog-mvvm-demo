package com.catganisation.catalog.injection.modules

import com.catganisation.catalog.presentation.catdetails.CatBreedDetailsActivity
import com.catganisation.catalog.presentation.cats.CatBreedsActivity
import com.catganisation.catalog.presentation.login.LoginActivity
import com.catganisation.catalog.presentation.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindCatBreedsActivity(): CatBreedsActivity

    @ContributesAndroidInjector
    abstract fun bindCatBreedDetailsActivity(): CatBreedDetailsActivity
}