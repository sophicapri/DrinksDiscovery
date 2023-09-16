package com.scapricorne.featuredrinks.presentation.albumlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.scapricorne.featuredrinks.api.DrinkRepository
import com.scapricorne.featuredrinks.presentation.drinklist.DrinkListViewModel
import com.scapricorne.featuredrinks.presentation.drinklist.DrinkPagingSource
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DrinkListViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: DrinkListViewModel
    private lateinit var pagingSource : DrinkPagingSource
    private var drinkRepository: DrinkRepository = mockk()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        pagingSource = DrinkPagingSource(drinkRepository)
        viewModel = DrinkListViewModel(testDispatcher, pagingSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test get drinks from paging source`() = runTest {
        // GIVEN
        pagingSource.load(PagingSource.LoadParams.Refresh(0, 25, false))

        // WHEN
        viewModel.drinks.first()

        coVerify(exactly = 1) { drinkRepository.getDrinks(any()) }
    }
}