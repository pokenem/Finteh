package com.example.finteh.navigation

enum class Screen {
    POPULAR,
    FILMDESC,
}

sealed class NavigationItem(val route: String) {
    object Popular : NavigationItem(Screen.POPULAR.name)
    object FilmDescPage : NavigationItem(Screen.FILMDESC.name)
}