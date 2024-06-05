package com.rakhlinskyi.natifeapp.core.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.rakhlinskyi.natifeapp.core.presentation.screens.search.SearchScreen
import com.rakhlinskyi.natifeapp.core.domain.models.Route
import com.rakhlinskyi.natifeapp.core.presentation.screens.gif.GifScreen
import com.rakhlinskyi.natifeapp.core.presentation.screens.home.HomeScreen
import com.rakhlinskyi.natifeapp.core.presentation.screens.home.HomeViewModel

@OptIn(ExperimentalPagingApi::class, ExperimentalCoilApi::class)
@Composable
fun AppNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    val homeViewModel: HomeViewModel = hiltViewModel()

    val allGifs = homeViewModel.getAllGifs.collectAsLazyPagingItems()

    NavHost(navController, startDestination = Route.HomeRoute.route) {
        composable(route = Route.HomeRoute.route) {
            HomeScreen(navController = navController, homeViewModel, allGifs)
        }
        composable(route = Route.SearchRoute.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Route.GifRoute(null).route,
            arguments = listOf(navArgument("gifIndex") { type = NavType.IntType })
        ) {
            GifScreen(navController = navController, allGifs, onGifDelete = {
                homeViewModel.removeGifById(it.id)
            })
        }
    }
}