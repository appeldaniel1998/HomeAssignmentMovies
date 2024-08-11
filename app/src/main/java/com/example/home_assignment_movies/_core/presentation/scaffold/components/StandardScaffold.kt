package com.example.home_assignment_movies._core.presentation.scaffold.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.home_assignment_movies._core.presentation.navigation.util.BottomNavigationDestinations
import com.example.home_assignment_movies._core.presentation.scaffold.ScaffoldUIEvent
import com.example.home_assignment_movies._core.presentation.scaffold.ScaffoldViewModel
import com.example.home_assignment_movies._core.presentation.util.ScaffoldConfig
import com.example.homeassignmentmovies.R

/**
 * A standard scaffold that contains a top app bar, a bottom navigation bar, and a snackbar host.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardScaffold(
    snackbarHostState: SnackbarHostState,
    scaffoldConfig: MutableState<ScaffoldConfig>,
    navController: NavController,
    viewModel: ScaffoldViewModel = hiltViewModel(),
    screenContent: @Composable () -> Unit
) {
    val uiState = viewModel.uiState.value

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, // snackbar host
        bottomBar = { // bottom navigation bar
            NavigationBar(modifier = Modifier.height(100.dp)) {
                BottomNavigationDestinations.list.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = uiState.navBarItemSelectedIndex == index,
                        onClick = { viewModel.onEvent(ScaffoldUIEvent.OnNavBarItemSelected(navController, index, item)) },
                        icon = { // icon of the navigation bar item
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(imageVector = item.icon, contentDescription = item.title.asString())
                                Text(text = item.title.asString(), fontSize = 12.sp)
                            }
                        }
                    )
                }
            }
        },
        topBar = { // top app bar
            TopAppBar(
                title = {
                    Text(
                        text = scaffoldConfig.value.topAppBarTitle.asString(),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                navigationIcon = {
                    if (scaffoldConfig.value.showBackButton) {
                        IconButton(onClick = { viewModel.onEvent(ScaffoldUIEvent.OnBackPress(navController)) }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back))
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF01b4e4)
                ),
            )
        })
    { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            screenContent()
        }
    }
}