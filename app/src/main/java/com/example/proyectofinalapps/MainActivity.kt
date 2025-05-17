package com.example.proyectofinalapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectofinalapps.ui.theme.AlertasAppTheme
import com.example.proyectofinalapps.ui.theme.navigation.Navigation
import com.example.proyectofinalapps.ui.theme.navigation.RouteScreen
import com.example.proyectofinalapps.ui.theme.screen.HomeAdmin
import com.example.proyectofinalapps.ui.theme.screen.HomeUser
import com.example.proyectofinalapps.ui.theme.screen.PendientesVerificacion
import com.example.proyectofinalapps.ui.theme.screen.Rechazo
import com.example.proyectofinalapps.ui.theme.screen.ResetPassword
import com.example.proyectofinalapps.ui.theme.screens.NewReportScreen
import com.example.proyectofinalapps.ui.theme.screens.SelectFileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                AlertasAppTheme {
                    //SelectFileScreen()
                    //LoginScreen(navigateToRegister = { }, navigateToForgotPasswordScreen = { })
                    //HomeScreen(navigateToNewReportScreen = { }, navigateToNotification = { })
                    Navigation()
                    //DetailsReportScreeen()
                    //RegisterScreen()
                    //ForgotPasswordScreen()
                    //EmailForgotPasswordScreen()
                    //NewReportScreen()
                    //ResetPassword(navigateToCodeVerification = {}, navigateToLoginScreenP = { })
                    //ConfarmationCode()
                    //VerificationDelete()
                    //MenuNotifications()
                    //Profile()
                    //Comentarios()
                    //Historial()
                    //PendientesVerificacion()
                    //Rechazo()
                    //Reportes()
                    //Notification()
                    /*HomeUser(navigateToHomeUser = { }, navigateToProfile = { },
                        navigateToNewReport = { }, navigateToNotifications = { },
                        navigateToDetailReports = { } )*/
                    //HomeAdmin (navigateToLogin = { }, navigateToNotifications = { }, navigateToMenuPendientes = { })
                    //CodeVerification()
                    //MenuReportes(navigateToMenuNotifications = {})
                }
            }
        }
    }