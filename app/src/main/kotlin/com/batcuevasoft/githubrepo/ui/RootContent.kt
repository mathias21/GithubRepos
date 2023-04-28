package com.batcuevasoft.githubrepo.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootContent(controller: NavHostController) {
    val startDestination = "mainNavigation"

    AnimatedNavHost(navController = controller, startDestination = startDestination) {
        MainNavigation.graph(this, controller)
    }
}