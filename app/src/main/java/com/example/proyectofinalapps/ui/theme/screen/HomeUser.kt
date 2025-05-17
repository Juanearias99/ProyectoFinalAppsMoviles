package com.example.proyectofinalapps.ui.theme.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
                1 -> HomeUserScreen(navigateToHomeUser)
                2 -> ProfileScreen(navigateToProfile)
                3 -> NewReportScreen(navigateToNewReport)
                4 -> NotificationScreen(navigateToNotifications)
                5 -> DetailReportsScreen(navigateToDetailReports)

            }
        }

        BottomNavigationBar(
            selectedIndex = selectedIndex,
            onItemSelected = {  selectedIndex = it }
        )
    }
}

@Composable
fun ReportMaxPage(
    context: Context
){


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
            mapViewportState = mapViewportState,
            //mapState = rememberMapState{
              //  gesturesSettings = GesturesSettings{pitchEnabled = true }
            //}
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
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Buscar...") },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
        }
    }
}

@Composable
fun HomeUserScreen(navigateTo: () -> Unit) {
    Button(
        onClick = navigateTo,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

    }
}

@Composable
fun ProfileScreen(navigateToProfile: () -> Unit) {
    Button(
        onClick = navigateToProfile,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ir al perfil")
    }
}

@Composable
fun NewReportScreen(navigateToNewReport: () -> Unit) {
    Button(
        onClick = navigateToNewReport,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ir a nuevo reporte")
    }
}

@Composable
fun NotificationScreen(navigateToNotifications: () -> Unit) {
    Button(
        onClick = navigateToNotifications,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ir a notificaciones")
    }
}

@Composable
fun DetailReportsScreen(navigateToDetailReports: () -> Unit) {
    Button(
        onClick = navigateToDetailReports,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Ir a Detalles De Reportes")
    }
}

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Mapa") },
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Menu, contentDescription = "Perfil") },
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Book, contentDescription = "Nuevo reporte") },
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Notificaciones") },
            selected = selectedIndex == 4,
            onClick = { onItemSelected(4) }
        )
    }
}
