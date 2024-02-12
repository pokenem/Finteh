package com.example.finteh.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.finteh.models.FilmListViewModel
import com.example.finteh.models.FilmViewModel

import com.example.finteh.ui_code.FilmPageLoading
import com.example.finteh.ui_code.popularPage

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Popular.route,
) {
    val filmListViewModel: FilmListViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Popular.route) {
            popularPage(navController, filmListViewModel)
        }
        composable(NavigationItem.FilmDescPage.route + "/{filmId}") { backStackEntry ->
            val filmId = backStackEntry.arguments?.getString("filmId")
            val filmViewModel = FilmViewModel(filmId!!.toInt())
            FilmPageLoading(navController, filmId.toInt(), filmViewModel)
        }
    }
}




