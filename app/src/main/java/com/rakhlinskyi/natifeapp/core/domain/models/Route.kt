package com.rakhlinskyi.natifeapp.core.domain.models

sealed class Route(val route: String) {
    data object HomeRoute : Route(route = "home_screen")
    data object SearchRoute : Route(route = "search_screen")
    data class GifRoute(val index: Int?) : Route(route = "gif_screen/${index ?: "{gifIndex}"}")
}