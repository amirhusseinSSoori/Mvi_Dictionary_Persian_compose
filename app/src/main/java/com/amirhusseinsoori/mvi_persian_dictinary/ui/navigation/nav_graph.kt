package com.amirhusseinsoori.mvi_persian_dictinary.ui.navigation

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.amirhusseinsoori.mvi_persian_dictinary.common.BackHandler
import com.amirhusseinsoori.mvi_persian_dictinary.ui.details.Details
import com.amirhusseinsoori.mvi_persian_dictinary.ui.intro.Intro
import com.amirhusseinsoori.mvi_persian_dictinary.ui.theme.DicTheme
import com.amirhusseinsoori.mvi_persian_dictinary.ui.words.WordScreen

import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.gson.Gson
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun InitialNavGraph(){
    val navController: NavHostController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = NavRoute.WordRoute.route){
        addIntro(navController)
        WordNavigation(navController)
        DetailNavigation()
    }

}

@ExperimentalAnimationApi
fun NavGraphBuilder.addIntro(navController: NavController) {
    composable(NavRoute.IntroRoute.route,
        enterTransition = { initial, _ ->
            when (initial.destination.route) {
                NavRoute.WordRoute.route ->
                    slideInHorizontally(
                        initialOffsetX = { 300 },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300))
                else -> null
            }
        },
        exitTransition = { _, target ->
            when (target.destination.route) {
                NavRoute.WordRoute.route ->
                    slideOutHorizontally(
                        targetOffsetX = { 300 },
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
                else -> null
            }
        },
        popEnterTransition = { initial, _ ->
            when (initial.destination.route) {
                NavRoute.WordRoute.route ->
                    slideInHorizontally(
                        initialOffsetX = { 300 },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300))
                else -> null
            }
        }) {
        Intro(navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.WordNavigation(
    navController: NavController
) {

    composable(
        route = NavRoute.WordRoute.route,
        exitTransition = { _, _ ->
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = { initial, _ ->
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
    ) {

        var ctx= LocalContext.current
        DicTheme{
//        BackHandler(onBack ={
//
//        })
            WordScreen(navigateToDetailsScreen = {
                navController.navigate("${NavRoute.DetailsRoute.route}/${Gson().toJson(it)}")
            })
        }

    }
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
fun NavGraphBuilder.DetailNavigation(
) {
    composable(
        route = NavRoute.DetailsRoute.route + "/{details}",
        arguments = NavRoute.DetailsRoute.arguments,
        enterTransition = { _, _ ->
            slideInHorizontally(
                initialOffsetX = { -300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = { _, target ->
            slideOutHorizontally(
                targetOffsetX = { -300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        Details()
    }
}