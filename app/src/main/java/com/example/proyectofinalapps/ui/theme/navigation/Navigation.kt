package com.example.proyectofinalapps.ui.theme.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalapps.ui.theme.screen.Comentarios
import com.example.proyectofinalapps.ui.theme.screen.HomeUser
import com.example.proyectofinalapps.ui.theme.screen.Notification
import com.example.proyectofinalapps.ui.theme.screen.Profile
import com.example.proyectofinalapps.ui.theme.screen.ResetPassword
import com.example.proyectofinalapps.ui.theme.screen.VerificationDelete
import com.example.proyectofinalapps.ui.theme.screens.DetailsReportScreeen
import com.example.proyectofinalapps.ui.theme.screens.LoginScreen
import com.example.proyectofinalapps.ui.theme.screens.NewReportScreen
import com.example.proyectofinalapps.ui.theme.screens.RegisterScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    Surface {

        NavHost(
            navController = navController,
            startDestination = RouteScreen.LoginScreen
        ) {

            composable<RouteScreen.LoginScreen> {
                LoginScreen(
                    navigateToRegister = { navController.navigate(RouteScreen.RegisterScreen) },
                    navigateToForgotPasswordScreen = { navController.navigate(RouteScreen.ResetPasswordScreen) },
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) }
                )
            }

            composable<RouteScreen.RegisterScreen> {
                RegisterScreen(
                    navigateToLoginScreenR = { navController.navigate(RouteScreen.LoginScreen) }
                )
            }

            composable<RouteScreen.ResetPasswordScreen> {
                ResetPassword(
                    navigateToLoginScreenP = { navController.navigate(RouteScreen.LoginScreen) }
                )
            }

            composable<RouteScreen.HomeUser> {
                HomeUser (
                    navigateToHomeUser = { navController.navigate(RouteScreen.LoginScreen)},
                    navigateToProfile = { navController.navigate(RouteScreen.Profile)},
                    navigateToNewReportNew = { navController.navigate(RouteScreen.NewReportScreen)},
                    navigateToNotifications = { navController.navigate(RouteScreen.Notification)},
                    navigateToDetailReports = { navController.navigate(RouteScreen.DetailReportsScreen)}
                )
            }

            composable<RouteScreen.Profile> {
                Profile(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser)},
                    navigateToDeleteProfile = { navController.navigate(RouteScreen.VerificationDelete)}
                )
            }

            composable<RouteScreen.NewReportScreen> {
                NewReportScreen(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser)}
                )
            }

            composable<RouteScreen.Notification> {
                Notification(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser)}
                )
            }

            composable<RouteScreen.DetailReportsScreen> {
                DetailsReportScreeen(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser)},
                    navigateToComentarios = {navController.navigate(RouteScreen.Comentarios)}
                )
            }

            composable<RouteScreen.Comentarios> {
                Comentarios(
                    navigateToDetailReportsSreen = {navController.navigate(RouteScreen.DetailReportsScreen)}
                )
            }

            composable<RouteScreen.VerificationDelete> {
                VerificationDelete(
                    navigateToProfile = {navController.navigate(RouteScreen.Profile)}
                )
            }

        }
    }
}
