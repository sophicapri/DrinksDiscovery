package com.scapricorne.featuredrinks.presentation.drink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.scapricorne.core.ui.theme.DrinkDiscoveryTheme
import com.scapricorne.featuredrinks.presentation.drinklist.DrinkListFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetailFragment : Fragment() {
    private val args by navArgs<DrinkListFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val drink = args.drink
        return ComposeView(requireActivity()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DrinkDiscoveryTheme {
                    DrinkDetailScreen(drink = drink) { onBackPressed() }
                }
            }
        }
    }

    private fun onBackPressed() {
        NavHostFragment.findNavController(this).navigateUp()
    }
}