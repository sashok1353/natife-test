package com.rakhlinskyi.natifeapp.core.presentation.screens.gif

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rakhlinskyi.natifeapp.core.domain.models.GifModel
import com.rakhlinskyi.natifeapp.core.presentation.screens.common.GifItem


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagingApi::class, ExperimentalCoilApi::class)
@Composable
fun GifScreen(
    navController: NavHostController,
    allGifs: LazyPagingItems<GifModel>,
    onGifDelete: (gifModel: GifModel) -> Unit
) {
    val currentGifIndex = navController.currentBackStackEntry?.arguments?.getInt("gifIndex")

    val pagerState = rememberPagerState(pageCount = {
        allGifs.itemCount
    }, initialPage = currentGifIndex ?: 0)

    Scaffold(
        topBar = {
            GifTopBar(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            HorizontalPager(modifier = Modifier.padding(it), state = pagerState, key = {
                allGifs[it]?.id
                    ?: it
            }, pageSize = PageSize.Fill) { index ->
                allGifs[index]?.let { gifModel ->
                    GifItem(
                        modifier = Modifier.fillMaxHeight(),
                        gifImg = gifModel.gifOptions.original.webp ?: "",
                        gifModel = gifModel,
                        onGifClick = {

                        },
                        onGifDelete = {
                            onGifDelete(gifModel)
                        })
                }
            }
        }
    )
}