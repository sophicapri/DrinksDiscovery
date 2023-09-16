package com.scapricorne.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val topBarHeight = 70.dp

@Composable
fun TopBar(onLeftIconPressed: () -> Unit = {},
           navLeftIcon: IconRes? = null,
           title: String,
           onRightIconPressed: () -> Unit = {},
           navRightIcon: IconRes? = null,
           modifier: Modifier = Modifier.background(color = MaterialTheme.colors.primary, shape = RoundedCornerShape(bottomStart = 48.dp))) {
    Box(Modifier.height(topBarHeight)) {
        Box(modifier = modifier) {
            navLeftIcon?.let {
                Row(Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .clickable(interactionSource = remember { MutableInteractionSource() },
                                       indication = rememberRipple(bounded = false),
                                       onClick = onLeftIconPressed),
                        imageVector = navLeftIcon.icon,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = navLeftIcon.contentDescription
                    )
                }
            }

            Row(Modifier
                    .fillMaxSize()
                    .padding(bottom = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(text = title, fontSize = 20.sp,
                     modifier = Modifier.fillMaxWidth(),
                     textAlign = TextAlign.Center, color = MaterialTheme.colors.onPrimary)
            }

            navRightIcon?.let {
                Row(Modifier
                        .fillMaxSize()
                        .padding(end = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End) {
                    Icon(
                        modifier = Modifier.clickable(interactionSource = remember { MutableInteractionSource() },
                                                      indication = rememberRipple(bounded = false),
                                                      onClick = onRightIconPressed),
                        imageVector = navRightIcon.icon,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = navRightIcon.contentDescription
                    )
                }
            }
        }
    }
}