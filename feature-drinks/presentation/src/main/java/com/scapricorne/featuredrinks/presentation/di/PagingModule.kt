package com.scapricorne.featuredrinks.presentation.di

import androidx.paging.PagingSource
import com.scapricorne.featuredrinks.api.DrinkRepository
import com.scapricorne.featuredrinks.api.model.IDrink
import com.scapricorne.featuredrinks.presentation.drinklist.DrinkPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    fun provideDrinkPagingSource(drinkRepository: DrinkRepository): PagingSource<Int, IDrink> {
        return DrinkPagingSource(drinkRepository)
    }
}