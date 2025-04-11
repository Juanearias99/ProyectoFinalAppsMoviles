package com.example.proyectofinalapps.ui.theme.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalapps.ui.theme.screen.CodeVerification
import com.example.proyectofinalapps.ui.theme.screen.Comentarios
import com.example.proyectofinalapps.ui.theme.screen.HomeAdmin
import com.example.proyectofinalapps.ui.theme.screen.HomeUser
import com.example.proyectofinalapps.ui.theme.screen.MenuNotifications
import com.example.proyectofinalapps.ui.theme.screen.MenuPendientes
import com.example.proyectofinalapps.ui.theme.screen.Notification
import com.example.proyectofinalapps.ui.theme.screen.PendientesVerificacion
import com.example.proyectofinalapps.ui.theme.screen.Profile
import com.example.proyectofinalapps.ui.theme.screen.Rechazo
import com.example.proyectofinalapps.ui.theme.screen.ResetPassword
import com.example.proyectofinalapps.ui.theme.screen.VerificatioImport
import com.example.proyectofinalapps.ui.theme.screen.VerificationDelete
import com.example.proyectofinalapps.ui.theme.screen.VerificationResult
import com.example.proyectofinalapps.ui.theme.screens.DetailsReportScreeen
import com.example.proyectofinalapps.ui.theme.screens.EmailForgotPasswordScreen
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
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) },
                    navigateToEmailForgotPassword = { navController.navigate(RouteScreen.EmailForgotPasswordScreen) },
                    naviagteToHomeAdmin = { navController.navigate(RouteScreen.HomeAdmin)}
                )
            }

            composable<RouteScreen.RegisterScreen> {
                RegisterScreen(
                    navigateToLoginScreenR = { navController.navigate(RouteScreen.LoginScreen) }
                )
            }

            composable<RouteScreen.ResetPasswordScreen> {
                ResetPassword(
                    navigateToCodeVerification = { navController.navigate(RouteScreen.CodeVerification) },
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
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser)},
                    navigateToMenuNotifications = {navController.navigate(RouteScreen.MenuNotifications)}
                )
            }

            composable<RouteScreen.DetailReportsScreen> {
                DetailsReportScreeen(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser)},
                    navigateToComentarios = {navController.navigate(RouteScreen.Comentarios)},
                    navigateToVerificationImport = {navController.navigate(RouteScreen.VerificationImport)},
                    navigateToVerificationResult = {navController.navigate(RouteScreen.VerificationResult)}
                )
            }

            composable<RouteScreen.Comentarios> {
                Comentarios(
                    navigateToMenuNotifications = {navController.navigate(RouteScreen.MenuNotifications)}
                )
            }

            composable<RouteScreen.VerificationDelete> {
                VerificationDelete(
                    navigateToProfile = {navController.navigate(RouteScreen.Profile)}
                )
            }

            composable<RouteScreen.EmailForgotPasswordScreen> {
                EmailForgotPasswordScreen(
                    navigateToCodeVerification = { navController.navigate(RouteScreen.CodeVerification) },
                    navigateToLoginScreen = { navController.navigate(RouteScreen.LoginScreen) }
                )
            }

            composable<RouteScreen.CodeVerification> {
                CodeVerification(
                    navigateToResetPassword = { navController.navigate(RouteScreen.ResetPasswordScreen) },
                    navigateToEmailForgotPassword = { navController.navigate(RouteScreen.EmailForgotPasswordScreen) }
                )
            }

            composable<RouteScreen.MenuNotifications> {
                MenuNotifications(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) },
                    navigateToComentarios = {navController.navigate(RouteScreen.Comentarios)},
                    navigateToDetailReports = { navController.navigate(RouteScreen.DetailReportsScreen)}
                )
            }

            composable<RouteScreen.VerificationImport> {
                VerificatioImport(
                    navigateToDetailReports = { navController.navigate(RouteScreen.DetailReportsScreen) }
                )
            }

            composable<RouteScreen.VerificationResult> {
                VerificationResult(
                    navigateToDetailReports = { navController.navigate(RouteScreen.DetailReportsScreen)}
                )
            }

            composable<RouteScreen.HomeAdmin>{
                HomeAdmin(
                    navigateToLoginScreen = { navController.navigate(RouteScreen.LoginScreen)},
                    NavigateToMenuPendientes = { navController.navigate(RouteScreen.MenuPendientes)}
                )
            }

            composable<RouteScreen.MenuPendientes>{
                MenuPendientes(
                    navigateToHomeAdmin = { navController.navigate(RouteScreen.HomeAdmin)},
                    navigateToPendientesVerificacion = { navController.navigate(RouteScreen.PendientesVerificacion)}
                )
            }

            composable<RouteScreen.PendientesVerificacion>{
                PendientesVerificacion(
                    navigateToMenuPendientes = { navController.navigate(RouteScreen.MenuPendientes)},
                    navigateToRechazo = { navController.navigate(RouteScreen.Rechazo)}
                )
            }

            composable<RouteScreen.Rechazo>{
                Rechazo(
                    navigateToPendientesVerificacion = { navController.navigate(RouteScreen.PendientesVerificacion)}
                )
            }
        }
    }
}
