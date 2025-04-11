package com.example.proyectofinalapps.ui.theme.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalapps.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAdmin(navigateToLoginScreen: () -> Unit, NavigateToMenuPendientes: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Volver",
                            modifier = Modifier
                                .clickable {
                                    navigateToLoginScreen()
                                }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.Map_Admin),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    NavigateToMenuPendientes()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0061FF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(45.dp),
                shape = RoundedCornerShape(8.dp),
            ) {

                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Icono de usuario"
                )
                Text(
                    text = "Lista de Verificaciones",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        NavigateToMenuPendientes()
                    }

                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    //navigateToNotifications()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0061FF),
                    contentColor = Color.White
                ),

                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(45.dp),
                shape = RoundedCornerShape(8.dp),
            ) {

                Icon(
                    imageVector = Icons.Rounded.Image,
                    contentDescription = "Icono de usuario"
                )
                Text(
                    text = "Notificaciones",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        //navigateToNotifications()
                    }

                )
            }

        }
    }
}