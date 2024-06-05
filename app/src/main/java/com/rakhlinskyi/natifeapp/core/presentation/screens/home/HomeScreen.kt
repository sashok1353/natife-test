package com.rakhlinskyi.natifeapp.core.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rakhlinskyi.natifeapp.core.domain.models.Route
import com.rakhlinskyi.natifeapp.core.presentation.screens.common.ListContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val allGifs = homeViewModel.getAllGifs.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Route.SearchRoute.route)
                }
            )
        },
        content = {
            if (allGifs.itemCount != 0) ListContent(
                modifier = Modifier.padding(it),
                items = allGifs,
                onGifClicked = { navController.navigate(Route.GifRoute(it).route) },
                onGifDelete = { homeViewModel.removeGifById(it.id) })
            else
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier =
                        Modifier.align(Alignment.Center),
                        text = "Something went wrong...",
                        fontSize = 26.sp
                    )
                }
        }
    )
}