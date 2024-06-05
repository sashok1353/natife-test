package com.rakhlinskyi.natifeapp.core.presentation.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rakhlinskyi.natifeapp.R
import com.rakhlinskyi.natifeapp.core.domain.models.GifModel

@ExperimentalCoilApi
@Composable
fun GifItem(
    modifier: Modifier,
    gifModel: GifModel,
    gifImg: String,
    onGifClick: (gifModel: GifModel) -> Unit,
    onGifDelete: (gifModel: GifModel) -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(gifImg)
            .crossfade(true)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .build(),
        contentScale = ContentScale.Crop
    )

    Box(
        modifier = modifier
            .clickable {
                onGifClick(gifModel)
            }
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "Gif image",
            contentScale = ContentScale.Crop
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(48.dp),
            onClick = { onGifDelete(gifModel) }) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Gif",
                tint = Color.Red
            )
        }
    }
}