package com.example.finteh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.finteh.models.FilmListViewModel
import com.example.finteh.navigation.AppNavHost
import com.example.finteh.ui.theme.FintehTheme

import com.example.finteh.ui_code.popularPage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FintehTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}






