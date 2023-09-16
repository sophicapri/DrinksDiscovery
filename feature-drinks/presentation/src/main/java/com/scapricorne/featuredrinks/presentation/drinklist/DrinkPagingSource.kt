package com.scapricorne.featuredrinks.presentation.drinklist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.scapricorne.featuredrinks.api.DrinkRepository
import com.scapricorne.featuredrinks.api.DrinkRepository.Companion.ITEMS_PER_PAGE
import com.scapricorne.featuredrinks.api.model.IDrink
import com.scapricorne.featuredrinks.presentation.model.toDomain

class DrinkPagingSource(private val drinkRepository: DrinkRepository) :
    PagingSource<Int, IDrink>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, IDrink> {
        return try {
            val position = params.key ?: STARTING_INDEX
            val drinks = drinkRepository.getDrinks(position)

            LoadResult.Page(
                data = drinks.map { it.toDomain() },
                prevKey = if (position == STARTING_INDEX) null else position - 1,
                nextKey = if (drinks.isEmpty() || drinks.size < ITEMS_PER_PAGE) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, IDrink>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_INDEX = 1
    }
}