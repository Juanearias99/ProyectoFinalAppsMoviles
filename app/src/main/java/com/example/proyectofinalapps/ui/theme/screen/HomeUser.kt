package com.example.proyectofinalapps.ui.theme.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinalapps.R
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

@Composable
fun HomeUser(
    navigateToHomeUser: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToNewReport: () -> Unit,
    navigateToNotifications: () -> Unit,
    navigateToDetailReports: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            when (selectedIndex) {
                0 -> MapScreen()
                1 -> NewReportScreen(navigateToNewReport)
                2 -> NotificationScreen(navigateToNotifications)
                3 -> ProfileScreen(navigateToProfile)
            }
        }

        BottomNavigationBar(
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
    }
}

@Composable
fun MapScreen() {
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
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp)
                .background(Color.White, shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SAFE",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(12.dp))

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar...") },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            IconButton(onClick = { /* acción buscar */ }) {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }

            IconButton(onClick = { /* acción logout */ }) {
                Icon(Icons.Default.Logout, contentDescription = "Cerrar sesión")
            }
        }
    }
}

@Composable
fun NewReportScreen(navigateToNewReport: () -> Unit) {
    Button(
        onClick = navigateToNewReport,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Ir a nuevo reporte")
    }
}

@Composable
fun NotificationScreen(navigateToNotifications: () -> Unit) {
    Button(
        onClick = navigateToNotifications,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Ir a notificaciones")
    }
}

@Composable
fun ProfileScreen(navigateToProfile: () -> Unit) {
    Button(
        onClick = navigateToProfile,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Ir al perfil")
    }
}

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            alwaysShowLabel = true
        )
        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) },
            icon = { Icon(Icons.Default.Book, contentDescription = "Reportes") },
            label = { Text("Reportes") },
            alwaysShowLabel = true
        )
        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) },
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Notificaciones") },
            label = { Text("Notificación") },
            alwaysShowLabel = true
        )
        NavigationBarItem(
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") },
            alwaysShowLabel = true
        )
    }
}
