package com.rakhlinskyi.natifeapp.core.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rakhlinskyi.natifeapp.core.domain.models.GifModel
import com.rakhlinskyi.natifeapp.core.domain.models.Route
import com.rakhlinskyi.natifeapp.core.presentation.screens.common.ListContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    allGifs: LazyPagingItems<GifModel>
) {

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Route.SearchRoute.route)
                }
            )
        },
        content = {
            ListContent(
                modifier = Modifier.padding(it),
                items = allGifs,
                onGifClicked = { navController.navigate(Route.GifRoute(it).route) },
                onGifDelete = { homeViewModel.removeGifById(it.id) })
        }
    )
}