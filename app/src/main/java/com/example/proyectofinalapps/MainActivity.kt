package com.example.proyectofinalapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.proyectofinalapps.ui.theme.AlertasAppTheme
import com.example.proyectofinalapps.ui.theme.navigation.Navigation
import com.example.proyectofinalapps.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    private val usersViewModel : UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                AlertasAppTheme {
                    Navigation(
                        usersViewModel = usersViewModel
                    )
                     }
            }
        }
    }