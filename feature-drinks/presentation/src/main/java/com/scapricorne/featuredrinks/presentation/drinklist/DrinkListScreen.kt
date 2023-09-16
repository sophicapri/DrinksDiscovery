package com.scapricorne.featuredrinks.presentation.drinklist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.scapricorne.core.ui.TopBar
import com.scapricorne.core.ui.topBarHeight
import com.scapricorne.featuredrinks.presentation.R
import com.scapricorne.featuredrinks.api.model.IDrink
import com.scapricorne.core.ui.R as CoreRes

@Composable
fun DrinkListScreen(pagingData: LazyPagingItems<IDrink>, onItemClick: (IDrink) -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            TopBar(title = stringResource(id = R.string.albums_of_the_day))
        }
        Box(modifier = Modifier.padding(top = topBarHeight, start = 20.dp)) {
            DrinkList(lazyDrinkItems = pagingData, onItemClick = onItemClick)
        }
    }
}

@Stable
interface VerticalScrollerState {
    var scrollPosition: Int
    var scrollRange: Int
}

fun VerticalScrollerState(): VerticalScrollerState =
    VerticalScrollerStateImpl()

private class VerticalScrollerStateImpl(
    scrollPosition: Int = 0,
    scrollRange: Int = 0
) : VerticalScrollerState {
    private var _scrollPosition by
    mutableStateOf(scrollPosition, structuralEqualityPolicy())

    override var scrollPosition: Int
        get() = _scrollPosition
        set(value) {
            _scrollPosition = value.coerceIn(0, scrollRange)
        }

    private var _scrollRange by
    mutableStateOf(scrollRange, structuralEqualityPolicy())

    override var scrollRange: Int
        get() = _scrollRange
        set(value) {
            require(value >= 0) { "$value must be > 0" }
            _scrollRange = value
        }
}

@Composable
fun VerticalScroller(
    verticalScrollerState: VerticalScrollerState =
        remember { VerticalScrollerState() }
) {

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrinkList(lazyDrinkItems: LazyPagingItems<IDrink>, onItemClick: (IDrink) -> Unit) {
    val lazyListState = lazyDrinkItems.rememberLazyListState()

    LazyVerticalGrid(
        state = lazyListState, cells = GridCells.Fixed(2)
    ) {
        items(lazyDrinkItems.itemCount) { index ->
            lazyDrinkItems[index]?.let {
                DrinkItem(drink = it, onItemClick)
            }
        }
        item {
            Spacer(modifier = Modifier.padding(top = 20.dp))
        }
    }
}

/* When using Paging3-Compose the scroll position is reset to 0 when the composable is recreated
*  (https://issuetracker.google.com/issues/177245496)
*  This is a workaround I have found here to fix this issue : https://issuetracker.google.com/issues/177245496#comment24
**/
@Composable
fun <T : Any> LazyPagingItems<T>.rememberLazyListState(): LazyListState {
    return when (itemCount) {
        0 -> remember(this) { LazyListState(0, 0) }
        else -> androidx.compose.foundation.lazy.rememberLazyListState()
    }
}

@Composable
fun DrinkItem(drink: IDrink, onItemClick: (IDrink) -> Unit) {
    Box(modifier = Modifier
        .padding(top = 20.dp, end = 20.dp)
        .fillMaxWidth()
        .clickable { onItemClick(drink) }) {
        Column(
            Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(drink.imageUrl)
                    .crossfade(true)
                    .crossfade(350).transformations(RoundedCornersTransformation(8F))
                    .build(),
                placeholder = painterResource(CoreRes.drawable.ic_default_image),
                contentDescription = stringResource(R.string.album_cover),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(170.dp)
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))
            val modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            Text(
                text = drink.name?: "undefined name", maxLines = 1, overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp, fontWeight = FontWeight.Medium, modifier = modifier
            )
            Text(
                text = drink.firstBrewed?: "first brewed", maxLines = 1, overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp, fontWeight = FontWeight.Light, modifier = modifier
            )
        }
    }
}