package com.scapricorne.featuredrinks.presentation.drink

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.scapricorne.core.ui.IconRes
import com.scapricorne.core.ui.TopBar
import com.scapricorne.core.ui.R as CoreRes
import com.scapricorne.featuredrinks.api.model.IDrink
import com.scapricorne.featuredrinks.presentation.R

@Composable
fun DrinkDetailScreen(drink: IDrink?, onBackPressed: () -> Unit) {
    if (drink == null) {
        Toast.makeText(LocalContext.current,
                       stringResource(R.string.unknown_error_occured),
                       Toast.LENGTH_LONG).show()
        onBackPressed()
    } else
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                    TopBar(
                        title = drink.name ?: stringResource(R.string.album),
                        navLeftIcon = IconRes(Icons.Default.ArrowBack, stringResource(R.string.previous_page_btn)),
                        onLeftIconPressed = onBackPressed,
                        modifier = Modifier.background(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(bottomEnd = 48.dp)))
                }
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp),
                           verticalArrangement = Arrangement.Center,
                           horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        Spacer(modifier = Modifier.padding(top = 48.dp))
                        Text(text = drink.tagline?: "tagLine", fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center,
                             fontSize = 24.sp, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(drink.imageUrl)
                                .transformations(RoundedCornersTransformation(8F))
                                .build(),
                            placeholder = painterResource(CoreRes.drawable.ic_default_image),
                            contentDescription = stringResource(id = R.string.album_cover),
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.size(270.dp),
                        )
                        Spacer(modifier = Modifier.padding(top = 38.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "Artist: ", fontWeight = FontWeight.SemiBold)

                                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = drink.foodPairing[0], textAlign = TextAlign.End)
                                        Spacer(modifier = Modifier.padding(end = 16.dp))
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(drink.imageUrl)
                                                .crossfade(true)
                                                .transformations(CircleCropTransformation())
                                                .build(),
                                            placeholder = painterResource(CoreRes.drawable.ic_default_image),
                                            contentDescription = stringResource(R.string.artist_picture),
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier.size(54.dp),
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.padding(top = 14.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = stringResource(R.string.number_of_tracks), fontWeight = FontWeight.SemiBold)
                                Text(text = drink.boilVolume.toString(), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                            }

                            Spacer(modifier = Modifier.padding(top = 27.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = stringResource(R.string.release_date), fontWeight = FontWeight.SemiBold)
                                Text(text = drink.description?: "desc", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                    }
                }
            }
        }
}
