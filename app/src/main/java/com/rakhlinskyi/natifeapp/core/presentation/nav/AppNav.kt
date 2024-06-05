package com.rakhlinskyi.natifeapp.core.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.rakhlinskyi.natifeapp.core.domain.models.Route
import com.rakhlinskyi.natifeapp.core.presentation.screens.gif.GifScreen
import com.rakhlinskyi.natifeapp.core.presentation.screens.home.HomeScreen
import com.rakhlinskyi.natifeapp.core.presentation.screens.search.SearchScreen

@OptIn(ExperimentalPagingApi::class, ExperimentalCoilApi::class)
@Composable
fun AppNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    NavHost(navController, startDestination = Route.HomeRoute.route) {
        composable(route = Route.HomeRoute.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Route.SearchRoute.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Route.GifRoute(null).route,
            arguments = listOf(navArgument("gifIndex") { type = NavType.IntType })
        ) {
            GifScreen(navController = navController)
        }
    }
}