package com.example.home_assignment_movies._core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.home_assignment_movies._core.presentation.components.StandardScaffold
import com.example.home_assignment_movies._core.presentation.navigation.nav_graphs.MainNavGraph
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.home_assignment_movies.ui.theme.HomeAssignmentMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeAssignmentMoviesTheme {
                val navController = rememberNavController()
                val scaffoldConfig = remember { mutableStateOf(ScaffoldConfig()) }
                val snackbarHostState = remember { SnackbarHostState() }
                StandardScaffold(
                    navController = navController,
                    scaffoldConfig = scaffoldConfig,
                    snackbarHostState = snackbarHostState
                ) {
                    MainNavGraph(navController = navController, scaffoldConfig = scaffoldConfig)
                }
            }
        }
    }
}
