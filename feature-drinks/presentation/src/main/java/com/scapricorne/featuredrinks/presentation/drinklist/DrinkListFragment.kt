package com.scapricorne.featuredrinks.presentation.drinklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.compose.collectAsLazyPagingItems
import com.scapricorne.core.ui.theme.DrinkDiscoveryTheme
import com.scapricorne.featuredrinks.api.model.IDrink
import com.scapricorne.featuredrinks.presentation.model.Drink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkListFragment : Fragment() {
    private val viewModel by viewModels<DrinkListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DrinkDiscoveryTheme {
                    DrinkListScreen(pagingData = viewModel.drinks.collectAsLazyPagingItems(), this@DrinkListFragment::onItemClick)
                }
            }
        }
    }

    private fun onItemClick(drink: IDrink) {
        NavHostFragment.findNavController(this).navigate(DrinkListFragmentDirections.goToDrinkDetail(drink as Drink))
    }
}