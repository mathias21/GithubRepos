package com.batcuevasoft.githubrepo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.batcuevasoft.githubrepo.ui.details.GithubRepoDetailsScreen
import com.batcuevasoft.githubrepo.ui.main.GithubRepoListScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
object MainNavigation {

    private const val name = "mainNavigation"
    const val REPO_ID_ARG = "repoId"

    fun graph(builder: NavGraphBuilder, controller: NavHostController) {
        builder.navigation(startDestination = Routes.REPO_LIST.routeName, route = name) {
            composable(
                Routes.REPO_LIST.routeName,
                enterTransition = {
                    slideInHorizontally(initialOffsetX = { -1000 })
                }, exitTransition = {
                    slideOutHorizontally(targetOffsetX = { -1000 })
                },
                popEnterTransition = {
                    slideInHorizontally(initialOffsetX = { -1000 })
                },
                popExitTransition = {
                    slideOutHorizontally(targetOffsetX = { 1000 })
                }
            ) {
                RepoListDestination(controller)
            }
            composable(
                Routes.REPO_DETAILS.routeName + "/{$REPO_ID_ARG}",
                arguments = listOf(navArgument(REPO_ID_ARG) { type = NavType.LongType }),
                enterTransition = {
                    slideInHorizontally(initialOffsetX = { 1000 })
                }, exitTransition = {
                    slideOutHorizontally(targetOffsetX = { 1000 })
                },
                popEnterTransition = {
                    slideInHorizontally(initialOffsetX = { -1000 })
                },
                popExitTransition = {
                    slideOutHorizontally(targetOffsetX = { 1000 })
                }
            ) {
                RepoDetailsDestination(controller)
            }
        }
    }
}

private fun NavHostController.navigateToRepoDetails(repoId: Long) {
    navigate(Routes.REPO_DETAILS.routeName + "/$repoId")
}

@Composable
private fun RepoListDestination(controller: NavHostController) {
    GithubRepoListScreen(
        onBackPressed = {
        controller.popBackStack()
    }, onRepoClick = {
        controller.navigateToRepoDetails(it)
    })
}

@Composable
private fun RepoDetailsDestination(controller: NavHostController) {
    GithubRepoDetailsScreen(
        onBackPressed = {
            controller.popBackStack()
        }
    )
}

private enum class Routes(val routeName: String) {
    REPO_LIST("repoList"),
    REPO_DETAILS("repoDetails");
}