package com.rakhlinskyi.natifeapp.core.presentation.screens.common

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.rakhlinskyi.natifeapp.core.domain.models.GifModel

@ExperimentalCoilApi
@Composable
fun ListContent(
    modifier: Modifier,
    items: LazyPagingItems<GifModel>,
    onGifClicked: (index: Int) -> Unit,
    onGifDelete: (gifModel: GifModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { index, gifModel ->
                gifModel.id
            }
        ) { index, gifModel ->
            gifModel?.gifOptions?.fixedHeight?.webp?.let { gifImg ->
                GifItem(
                    modifier = Modifier.height(300.dp),
                    gifImg = gifImg,
                    gifModel = gifModel,
                    onGifDelete = { onGifDelete(gifModel) },
                    onGifClick = { onGifClicked(index) })
            }
        }
    }
}
