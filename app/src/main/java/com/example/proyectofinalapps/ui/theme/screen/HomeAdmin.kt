package com.example.proyectofinalapps.ui.theme.screen

import android.content.Context
import android.icu.text.AlphabeticIndex
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinalapps.R
import com.example.proyectofinalapps.model.Notification
import com.example.proyectofinalapps.ui.theme.navigation.RouteScreen
import com.example.proyectofinalapps.ui.theme.screens.LoginScreen
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings

@Composable
fun HomeAdmin(
    navigateToLogin: () -> Unit,
    navigateToNotifications: () -> Unit,
    navigateToMenuPendientes: () -> Unit
) {

    var selectedIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            when (selectedIndex) {
                0 -> MapScreen()
                1 -> LoginScreen(navigateToLogin)
                2 -> NotificationScreen(navigateToNotifications)
                3 -> MenuPendientes(navigateToMenuPendientes)
            }
        }

        BottomNavigationBar(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it })
    }
}

@Composable
fun reportMaxPage(
    context: Context
) {
}

@Composable
fun MapScreenAdmin() {
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(8.0)
            center(Point.fromLngLat(-75.7358251, 4.4721139))
            pitch(45.0)
        }
    }

    val markerResourceId = R.drawable.red_marker
    val marker = rememberIconImage(
        key = markerResourceId,
        painter = painterResource(markerResourceId)
    )

    var searchQuery by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        MapboxMap(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = mapViewportState
        ) {
            PointAnnotation(point = Point.fromLngLat(-75.7358251, 4.4721139)) {
                iconImage = marker
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar..") },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(16.dp))
            Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
        }
    }
}

@Composable
fun LoginScreen(navigateTo: () -> Unit) {
    Button(
        onClick = navigateTo,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
    }
}

@Composable
fun NotificationsScreen(navigateToNotifications: () -> Unit) {
    Button(
        onClick = navigateToNotifications,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Ir a las notificaciones")
    }
}

@Composable
fun MenuPendientes(navigateToMenuPendientes: () -> Unit) {
    Button(
        onClick = navigateToMenuPendientes,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Text(text = "Ir al Menu Pendientes")
    }
}

