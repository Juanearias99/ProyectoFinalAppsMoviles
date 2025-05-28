import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.proyectofinalapps.model.Report
import com.example.proyectofinalapps.ui.theme.components.Map
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState

@Composable
fun HomeUser(
    navigateToHomeUser: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToNewReport: () -> Unit,
    navigateToNotifications: () -> Unit,
    logout: () -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            when (selectedIndex) {
                0 -> MapScreen(onLogout = logout)
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
fun MapScreen(onLogout: () -> Unit) {
    val mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(7.0)
            center(Point.fromLngLat(-75.6491181, 4.4687891))
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Map(
            modifier = Modifier.fillMaxSize(),
            mapViewportState = mapViewportState,
            centerUserLocation = true,
            reports = listOf<Report>(),
            onNavigateToDetail = { }
        )

        Button(
            onClick = onLogout,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Cerrar Sesión", color = Color.White)
        }
    }
}


@Composable
fun NewReportScreen(navigateToNewReport: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = navigateToNewReport) {
            Text("Ir a nuevo reporte")
        }
    }
}

@Composable
fun NotificationScreen(navigateToNotifications: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = navigateToNotifications) {
            Text("Ir a notificaciones")
        }
    }
}

@Composable
fun ProfileScreen(navigateToProfile: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = navigateToProfile) {
            Text("Ir al perfil")
        }
    }
}

@Composable
fun BottomNavigationBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar(containerColor = Color.White, tonalElevation = 4.dp) {
        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) },
            icon = { Icon(Icons.Default.Book, contentDescription = "Reportes") },
            label = { Text("Reportes") }
        )
        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) },
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Notificaciones") },
            label = { Text("Notificación") }
        )
        NavigationBarItem(
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )
    }
}