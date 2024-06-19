package com.example.login_page_auth.navigation

import AddScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login_page_auth.ui.auth.MainScreen
import com.example.login_page_auth.ui.detail.DetailDestination
import com.example.login_page_auth.ui.detail.DetailDestination.petId
import com.example.login_page_auth.ui.detail.DetailScreen
import com.example.login_page_auth.ui.edit.EditDestination
import com.example.login_page_auth.ui.list.ListScreen
import com.example.login_page_auth.ui.edit.EditScreen
import com.example.login_page_auth.ui.groooming.GroomingScreen
import com.example.login_page_auth.ui.home.HomeScreen
import com.example.login_page_auth.ui.login.IgViewModel
import com.example.login_page_auth.ui.login.LoginScreen


@Composable
fun PengelolaHalaman (navController: NavController = rememberNavController(),isLoggedIn: Boolean){
    val viewModel = hiltViewModel<IgViewModel>()



    NavHost(
        navController = navController as NavHostController,
        startDestination = if (isLoggedIn)"HomeScreen" else "MainScreen"
    ) {
        composable("MainScreen") {
            MainScreen(navController, viewModel)
        }
        composable("LoginPage") {
            LoginScreen(navController , viewModel )
        }
        composable("HomeScreen") {
            HomeScreen(navController = navController)
        }
        composable("PetHotelScreen"){
            AddScreen(
                navigateBack = {navController.popBackStack()})
        }
        composable("AllDataPet"){
            ListScreen(
                navigateBack = {navController.popBackStack()},
                navigateToItemEntry = {navController.navigate("DetailScreen")},
                navController = navController
            )
        }
        composable("Product"){
            GroomingScreen(
                navigateBack = {navController.popBackStack()})
        }
        composable("EditScreen/{$petId}"){
            EditScreen(
                navigateBack = {navController.popBackStack()},
                onNavigateUp = {navController.navigate("${EditDestination.route}/$petId")})

        }
        composable("DetailScreen/{$petId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString(DetailDestination.petId)
            petId?.let {
                DetailScreen(
                    navigateToEditItem = { navController.navigate("${EditDestination.route}/$petId") },
                    navigateBack = { navController.popBackStack() },
                    )
            }
        }

        composable(
            route = DetailDestination.routeWithArgs,
                   arguments = listOf(navArgument(DetailDestination.petId) {
                       type = NavType.StringType
                   })
        ){ backStackEntry ->
            val petId = backStackEntry.arguments?.getString(DetailDestination.petId)
            petId?.let {
                DetailScreen(
                    navigateToEditItem = { navController.navigate("${EditDestination.route}/$petId") },
                    navigateBack = { navController.popBackStack() },
                    )
            }
        }
        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.petId){
                type  = NavType.StringType
            })
        ) { backStackEntry ->
            val petId = backStackEntry.arguments?.getString(EditDestination.petId)
            petId?.let {
                EditScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = {navController.navigateUp()}
                )
            }

        }
    }
}