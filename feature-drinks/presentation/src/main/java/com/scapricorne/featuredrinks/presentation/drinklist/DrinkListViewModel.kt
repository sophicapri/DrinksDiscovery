package com.scapricorne.featuredrinks.presentation.drinklist

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.scapricorne.featuredrinks.api.model.IDrink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    mainDispatcher: CoroutineDispatcher,
    pagingSource: PagingSource<Int, IDrink>
) : ViewModel() {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + mainDispatcher)
    val drinks = Pager(config = PagingConfig(pageSize = 25)) {
        pagingSource
    }.flow.cachedIn(scope = scope)

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}