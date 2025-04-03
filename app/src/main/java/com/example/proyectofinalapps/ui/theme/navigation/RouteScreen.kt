package com.example.proyectofinalapps.ui.theme.navigation

import android.app.Notification
import kotlinx.serialization.Serializable

sealed class  RouteScreen {

    @Serializable
    data object HomeScreen : RouteScreen()

    @Serializable
    data object LoginScreen : RouteScreen()

    @Serializable
    data object RegisterScreen : RouteScreen()

    @Serializable
    data object NewReportScreen : RouteScreen()

    @Serializable
    data object ResetPasswordScreen : RouteScreen()

    @Serializable
    data object DetailReportsScreen : RouteScreen()

    @Serializable
    data object Comentarios : RouteScreen()

    @Serializable
    data object ConfarmationCode : RouteScreen()

    @Serializable
    data object EmailForgotPasswordScreen : RouteScreen()

    @Serializable
    data object Historial : RouteScreen()

    @Serializable
    data object HomeAdmin : RouteScreen()

    @Serializable
    data object HomeUser : RouteScreen()

    @Serializable
    data object MenuNotifications : RouteScreen()

    @Serializable
    data object Notification : RouteScreen()

    @Serializable
    data object PendientesVerificacion : RouteScreen()

    @Serializable
    data object Profile : RouteScreen()

    @Serializable
    data object Rechazo : RouteScreen()

    @Serializable
    data object SelectFileScreen : RouteScreen()

    @Serializable
    data object Reportes : RouteScreen()

    @Serializable
    data object VerificationDelete : RouteScreen()
}