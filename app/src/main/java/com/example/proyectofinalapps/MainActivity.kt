package com.example.proyectofinalapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.proyectofinalapps.ui.theme.AlertasAppTheme
import com.example.proyectofinalapps.ui.theme.ProyectoFinalAppsTheme
import com.example.proyectofinalapps.ui.theme.navigation.Navigation
import com.example.proyectofinalapps.ui.theme.screen.Comentarios
import com.example.proyectofinalapps.ui.theme.screen.ConfarmationCode
import com.example.proyectofinalapps.ui.theme.screen.Historial
import com.example.proyectofinalapps.ui.theme.screen.HomeAdmin
import com.example.proyectofinalapps.ui.theme.screen.HomeUser
import com.example.proyectofinalapps.ui.theme.screen.MenuNotifications
import com.example.proyectofinalapps.ui.theme.screen.Notification
import com.example.proyectofinalapps.ui.theme.screen.PendientesVerificacion
import com.example.proyectofinalapps.ui.theme.screen.Profile
import com.example.proyectofinalapps.ui.theme.screen.Rechazo
import com.example.proyectofinalapps.ui.theme.screen.Reportes
import com.example.proyectofinalapps.ui.theme.screen.ResetPassword
import com.example.proyectofinalapps.ui.theme.screen.VerificationDelete
import com.example.proyectofinalapps.ui.theme.screens.DetailsReportScreeen
import com.example.proyectofinalapps.ui.theme.screens.EmailForgotPasswordScreen
import com.example.proyectofinalapps.ui.theme.screens.LoginScreen
import com.example.proyectofinalapps.ui.theme.screens.NewReportScreen
import com.example.proyectofinalapps.ui.theme.screens.RegisterScreen
import com.example.proyectofinalapps.ui.theme.screens.SelectFileScreen

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
                }
            }
        }
    }