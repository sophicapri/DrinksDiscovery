package com.scapricorne.featuredrinks.di

import com.scapricorne.featuredrinks.api.DrinkRepository
import com.scapricorne.featuredrinks.data.DrinkWebService
import com.scapricorne.featuredrinks.data.DrinkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DrinksDataModule {

    @Provides
    fun provideDrinkWebService(retrofit: Retrofit) : DrinkWebService = retrofit.create(
        DrinkWebService::class.java)
    
    @Provides
    fun provideDrinkRepository(drinkWebService: DrinkWebService) : DrinkRepository {
        return DrinkRepositoryImpl(drinkWebService)
    }
}