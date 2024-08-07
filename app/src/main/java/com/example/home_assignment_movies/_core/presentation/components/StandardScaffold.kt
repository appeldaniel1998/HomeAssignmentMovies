package com.example.home_assignment_movies._core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    snackbarHostState: SnackbarHostState,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    navController: NavController,
    screenContent: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            //TODO: Implement bottom bar
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = scaffoldConfig.value.topAppBarTitle,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                navigationIcon = {
                    if (scaffoldConfig.value.showBackButton) {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
            )
        })
    { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            screenContent()
        }
    }
}