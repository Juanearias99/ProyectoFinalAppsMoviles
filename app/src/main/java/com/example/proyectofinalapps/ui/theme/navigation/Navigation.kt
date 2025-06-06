package com.example.proyectofinalapps.ui.theme.navigation

import HomeUser
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalapps.model.Role
import com.example.proyectofinalapps.ui.theme.screen.CodeVerification
import com.example.proyectofinalapps.ui.theme.screen.Comentarios
import com.example.proyectofinalapps.ui.theme.screen.HomeAdmin
import com.example.proyectofinalapps.ui.theme.screen.MenuNotifications
import com.example.proyectofinalapps.ui.theme.screen.MenuPendientes
import com.example.proyectofinalapps.ui.theme.screen.Notification
import com.example.proyectofinalapps.ui.theme.screen.PendientesVerificacion
import com.example.proyectofinalapps.ui.theme.screen.Profile
import com.example.proyectofinalapps.ui.theme.screen.Rechazo
import com.example.proyectofinalapps.ui.theme.screen.ResetPassword
import com.example.proyectofinalapps.ui.theme.screen.VerificationImport
import com.example.proyectofinalapps.ui.theme.screen.VerificationDelete
import com.example.proyectofinalapps.ui.theme.screen.VerificationResult
import com.example.proyectofinalapps.ui.theme.screens.DetailsReportScreen
import com.example.proyectofinalapps.ui.theme.screens.EmailForgotPasswordScreen
import com.example.proyectofinalapps.ui.theme.screens.LoginScreen
import com.example.proyectofinalapps.ui.theme.screens.NewReportScreen
import com.example.proyectofinalapps.ui.theme.screens.RegisterScreen
import com.example.proyectofinalapps.utils.SharedPreferencesUtils
import com.example.proyectofinalapps.viewmodel.MainViewModel
import com.example.proyectofinalapps.viewmodel.UserViewModel


@Composable
fun Navigation() {
    val mainViewModel: MainViewModel = viewModel()
    //val LocalMainViewModel =
    //  staticCompositionLocalOf<MainViewModel> { error("MainViewModel is not provided") }
    val LocalMainViewModel =
        staticCompositionLocalOf<MainViewModel> { error("MainViewModel is not provided") }
    val context = LocalContext.current
    val navController = rememberNavController()
    val user = SharedPreferencesUtils.getPreference(context)
    var startDestination: RouteScreen = RouteScreen.LoginScreen

    if (!user.isEmpty()) {
        val role = user.get("role")

        startDestination = if (role == "ADMIN") {
            RouteScreen.HomeAdmin
        } else {
            RouteScreen.HomeUser
        }
    }

    Surface {

        CompositionLocalProvider(LocalMainViewModel provides mainViewModel) { }
        NavHost(
            navController = navController,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            startDestination = startDestination
        ) {

            composable<RouteScreen.LoginScreen> {
                LoginScreen(
                    usersViewModel = mainViewModel.userViewModel,
                    navigateToRegister = {
                        navController.navigate(RouteScreen.RegisterScreen)
                    },
                    navigateToEmailForgotPassword = {
                        navController.navigate(RouteScreen.EmailForgotPasswordScreen)
                    },

                    navigateToHomeScreen = {
                        val currentUser = mainViewModel.userViewModel.currentUser.value
                        if (currentUser != null) {
                            val home = if (currentUser.role == Role.ADMIN) {
                                RouteScreen.HomeAdmin
                            } else {
                                RouteScreen.HomeUser
                            }

                            navController.navigate(home) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }else{
                            navController.navigate(RouteScreen.HomeUser)
                        }
                    }
                )
            }

            composable<RouteScreen.RegisterScreen> {
                RegisterScreen(
                    usersViewModel = mainViewModel.userViewModel,
                    navigateToLoginScreenR = {
                        navController.navigate(RouteScreen.LoginScreen)
                    }
                )
            }

            composable<RouteScreen.ResetPasswordScreen> {
                ResetPassword(
                    navigateToCodeVerification = { navController.navigate(RouteScreen.CodeVerification) },
                    navigateToLoginScreenP = { navController.navigate(RouteScreen.LoginScreen) },
                    navigateToHomeScreen = { navController.navigate(RouteScreen.HomeScreen) }
                )
            }


            composable<RouteScreen.HomeUser> {
                HomeUser(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) },
                    navigateToProfile = { navController.navigate(RouteScreen.Profile) },
                    navigateToNewReport = { navController.navigate(RouteScreen.NewReportScreen) },
                    navigateToNotifications = { navController.navigate(RouteScreen.Notification) },
                    logout = {
                        SharedPreferencesUtils.clearPreference(context)
                        mainViewModel.userViewModel.logout()

                        navController.navigate(RouteScreen.LoginScreen) {
                            popUpTo(0) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable<RouteScreen.Profile> {
                Profile(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) },
                    navigateToDeleteProfile = { navController.navigate(RouteScreen.VerificationDelete) }
                )
            }

            composable<RouteScreen.NewReportScreen> {
                NewReportScreen(
                    reportViewModel = mainViewModel.reportViewModel,
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) }
                )
            }

            composable<RouteScreen.Notification> {
                Notification(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) },
                    navigateToMenuNotifications = { navController.navigate(RouteScreen.MenuNotifications) }
                )
            }

            composable <RouteScreen.DetailReportsScreen> {
                DetailsReportScreen(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) },
                    navigateToComentarios = { navController.navigate(RouteScreen.Comentarios) },
                    navigateToVerificationImport = { navController.navigate(RouteScreen.VerificationImport) },
                    navigateToVerificationResult = { navController.navigate(RouteScreen.VerificationResult) }
                )
            }

            composable <RouteScreen.Comentarios> {
                Comentarios(
                    navigateToMenuNotifications = { navController.navigate(RouteScreen.MenuNotifications) }
                )
            }

            composable <RouteScreen.VerificationDelete> {
                VerificationDelete(
                    navigateToProfile = { navController.navigate(RouteScreen.Profile) }
                )
            }

            composable <RouteScreen.EmailForgotPasswordScreen> {
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

            composable <RouteScreen.MenuNotifications> {
                MenuNotifications(
                    navigateToHomeUser = { navController.navigate(RouteScreen.HomeUser) },
                    navigateToComentarios = { navController.navigate(RouteScreen.Comentarios) },
                    navigateToDetailReports = { navController.navigate(RouteScreen.DetailReportsScreen) }
                )
            }

            composable <RouteScreen.VerificationImport> {
                VerificationImport(
                    navigateToDetailReports = { navController.navigate(RouteScreen.DetailReportsScreen) }
                )
            }
            composable <RouteScreen.VerificationResult> {
                VerificationResult(
                    navigateToDetailReports = { navController.navigate(RouteScreen.DetailReportsScreen) }
                )
            }


            composable<RouteScreen.HomeAdmin> {
                HomeAdmin(
                    navigateToLogin = { navController.navigate(RouteScreen.LoginScreen) },
                    navigateToNotifications = { navController.navigate(RouteScreen.Notification) },
                    navigateToMenuPendientes = { navController.navigate(RouteScreen.MenuPendientes) },
                    logout = {
                        SharedPreferencesUtils.clearPreference(context)
                        navController.navigate(RouteScreen.LoginScreen) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable<RouteScreen.MenuPendientes> {
                MenuPendientes(
                    navigateToHomeAdmin = { navController.navigate(RouteScreen.HomeAdmin) },
                    navigateToPendientesVerificacion = { navController.navigate(RouteScreen.PendientesVerificacion) }
                )
            }

            composable <RouteScreen.PendientesVerificacion> {
                PendientesVerificacion(
                    navigateToMenuPendientes = { navController.navigate(RouteScreen.MenuPendientes) },
                    navigateToRechazo = { navController.navigate(RouteScreen.Rechazo) }
                )
            }

            composable <RouteScreen.Rechazo> {
                Rechazo(
                    navigateToPendientesVerificacion = { navController.navigate(RouteScreen.PendientesVerificacion) }
                )
            }
        }
    }
}