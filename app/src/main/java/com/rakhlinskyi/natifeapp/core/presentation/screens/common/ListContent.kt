package com.rakhlinskyi.natifeapp.core.presentation.screens.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rakhlinskyi.natifeapp.R
import com.rakhlinskyi.natifeapp.core.domain.models.GifModel
import com.rakhlinskyi.natifeapp.core.domain.models.Route

@ExperimentalCoilApi
@Composable
fun ListContent(
    modifier: Modifier,
    items: LazyPagingItems<GifModel>,
    onGifClicked: (index: Int) -> Unit,
    onGifDelete: (gifModel: GifModel) -> Unit,
) {
    Log.d("Error", items.loadState.toString())
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
