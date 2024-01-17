package com.nabil.movienerd.ui.nav

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailFilm : Screen("home/{filmId}") {
        fun createRoute(filmId: Long) = "home/$filmId"
    }
}