package com.example.proyectofinalapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.proyectofinalapps.ui.theme.AlertasAppTheme
import com.example.proyectofinalapps.ui.theme.navigation.Navigation
import com.example.proyectofinalapps.viewmodel.MainViewModel
import com.example.proyectofinalapps.viewmodel.ReportsViewModel
import com.example.proyectofinalapps.viewmodel.UsersViewModel

class MainActivity : ComponentActivity() {

    private val usersViewModel : UsersViewModel by viewModels()
    private val reportsViewModel : ReportsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)

        val mainViewModel = MainViewModel(
            usersViewModel,
            reportsViewModel
        )

        enableEdgeToEdge()
        setContent {

                AlertasAppTheme {
                    Navigation(
                       mainViewModel = mainViewModel
                    )
                }
            }
        }
    }