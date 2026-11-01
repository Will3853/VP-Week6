package com.william.week6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.william.week6.ui.screen.CommunitiesScreen
import com.william.week6.ui.screen.DashboardScreen
import com.william.week6.ui.screen.ProjectScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DashBoardRoute,
        modifier = modifier
    ) {
        composable<DashBoardRoute> {
            DashboardScreen(
                onCommunities = {
                    navController.navigate(CommunitiesRoute)
                }
            )
        }

        composable<CommunitiesRoute> {
            CommunitiesScreen(
                onHome = {
                    navController.navigate(DashBoardRoute)
                },
                onProject = { projectId ->
                    navController.navigate(ProjectRoute(projectId))
                }
            )
        }
        composable<ProjectRoute> {
            ProjectScreen(onBack = {navController.popBackStack()})
        }
    }
}

