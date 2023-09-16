package com.scapricorne.featuredrinks.api

import com.scapricorne.featuredrinks.api.model.IDrink

interface DrinkRepository {
    suspend fun getDrinks(pageIndex: Int) : List<IDrink>
}