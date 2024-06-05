package com.rakhlinskyi.natifeapp.core.presentation.screens.search

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rakhlinskyi.natifeapp.core.domain.models.Route
import com.rakhlinskyi.natifeapp.core.presentation.screens.common.ListContent

@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchGifs(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(modifier = Modifier.padding(it), items = searchedImages, onGifDelete = {
                Toast.makeText(
                    context,
                    "Hey, in this page you can't delete any items.",
                    Toast.LENGTH_SHORT
                ).show()
            }, onGifClicked = { navController.navigate(Route.GifRoute(it).route) })
        }
    )
}