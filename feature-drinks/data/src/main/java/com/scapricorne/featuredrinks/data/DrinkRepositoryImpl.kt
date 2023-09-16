package com.scapricorne.featuredrinks.data

import com.scapricorne.featuredrinks.api.DrinkRepository
import com.scapricorne.featuredrinks.data.model.DrinkDto
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(private val drinkWebService: DrinkWebService) :
    DrinkRepository {
    override suspend fun getDrinks(pageIndex: Int): List<DrinkDto> {
        return drinkWebService.getDrinks(pageIndex)
    }
}