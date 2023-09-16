package com.scapricorne.featuredrinks.data

import com.scapricorne.featuredrinks.api.DrinkRepository
import com.scapricorne.featuredrinks.data.model.DrinkDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DrinkRepositoryTest {
    private lateinit var drinkRepository : DrinkRepository
    private val drinkWebService: DrinkWebService = mockk()

    @Before
    fun setUp() {
        drinkRepository = DrinkRepositoryImpl(drinkWebService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test get drinks from WS returns DrinkResponse`(): Unit = runTest {
        // GIVEN
        val drinkResponse = listOf(DrinkDto(
            id = null,
            name = null,
            tagline = null,
            firstBrewed = null,
            description = null,
            imageUrl = null,
            volume = null,
            boilVolume = null,
            foodPairing = listOf(),
            brewersTips = null,
            contributedBy = null
        ))
        coEvery { drinkRepository.getDrinks(STARTING_INDEX) } returns drinkResponse

        // WHEN
        val response = drinkRepository.getDrinks(STARTING_INDEX)

        // THEN
        coVerify (exactly = 1) { drinkWebService.getDrinks(STARTING_INDEX) }
        assertEquals(drinkResponse, response)
    }

    companion object {
        private const val STARTING_INDEX = 1
    }
}