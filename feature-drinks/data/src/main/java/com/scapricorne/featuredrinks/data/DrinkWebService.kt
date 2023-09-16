package com.scapricorne.featuredrinks.data

import com.scapricorne.featuredrinks.data.model.DrinkDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkWebService {
    @GET("beers")
    suspend fun getDrinks(@Query("page") pageIndex: Int): List<DrinkDto>
}