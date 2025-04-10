package com.example.proyectofinalapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectofinalapps.ui.theme.AlertasAppTheme
import com.example.proyectofinalapps.ui.theme.navigation.Navigation
import com.example.proyectofinalapps.ui.theme.navigation.RouteScreen
import com.example.proyectofinalapps.ui.theme.screen.CodeVerification

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                AlertasAppTheme {
                    //SelectFileScreen()
                    //LoginScreen(navigateToRegister = { }, navigateToForgotPasswordScreen = { })
                    Navigation()
                    //DetailsReportScreeen()
                    //RegisterScreen()
                    //ForgotPasswordScreen()
                    //EmailForgotPasswordScreen()
                    //NewReportScreen()
                    //ResetPassword()
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
                    //HomeUser()
                    //HomeAdmin()
                    //CodeVerification()
                    //MenuReportes(navigateToMenuNotifications = {})
                }
            }
        }
    }