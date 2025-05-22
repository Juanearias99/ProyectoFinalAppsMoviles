package com.example.proyectofinalapps.ui.theme.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AdminPanelSettings
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinalapps.utils.SharedPreferencesUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeAdmin(
    navigateToLogin: () -> Unit,
    navigateToNotifications: () -> Unit,
    navigateToMenuPendientes: () -> Unit,
    logout: () -> Unit
) {
    val context = LocalContext.current
    BackHandler { logout() }

    AdminDashboard(
        onNavigatePendientes = navigateToMenuPendientes,
        onNavigateNotifications = navigateToNotifications,
        onLogout = {
            SharedPreferencesUtils.clearPreference(context)
            navigateToLogin()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboard(
    onNavigatePendientes: () -> Unit,
    onNavigateNotifications: () -> Unit,
    onLogout: () -> Unit
) {
    val animatedItems = remember { mutableStateListOf(false, false, false, false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        scope.launch {
            animatedItems.forEachIndexed { index, _ ->
                delay(100L * index)
                animatedItems[index] = true
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.AdminPanelSettings,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Panel Administrativo",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.shadow(4.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFF8F9FA),
                                Color(0xFFE9ECEF)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Admin status card
                AnimatedVisibility(
                    visible = animatedItems[0],
                    enter = fadeIn() + slideInVertically(
                        initialOffsetY = { -40 },
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                    ),
                    exit = fadeOut()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.AccountCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(
                                    text = "Administrador",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "Acceso completo al sistema",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                }

                // Dashboard label
                AnimatedVisibility(
                    visible = animatedItems[1],
                    enter = fadeIn() + slideInVertically(
                        initialOffsetY = { -40 },
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                    ),
                    exit = fadeOut()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Dashboard,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Acciones Principales",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                // Main action cards
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Pendientes Card
                    AnimatedVisibility(
                        visible = animatedItems[2],
                        enter = fadeIn() + slideInVertically(
                            initialOffsetY = { 100 },
                            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                        ),
                        exit = fadeOut(),
                        modifier = Modifier.weight(1f)
                    ) {
                        ModernAdminCard(
                            icon = Icons.Rounded.List,
                            title = "Pendientes",
                            description = "Verificación de solicitudes",
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            iconTint = MaterialTheme.colorScheme.secondary,
                            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            onClick = onNavigatePendientes
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))


                    AnimatedVisibility(
                        visible = animatedItems[2],
                        enter = fadeIn() + slideInVertically(
                            initialOffsetY = { 100 },
                            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                        ),
                        exit = fadeOut(),
                        modifier = Modifier.weight(1f)
                    ) {
                        ModernAdminCard(
                            icon = Icons.Rounded.NotificationsActive,
                            title = "Notificaciones",
                            description = "Gestionar avisos",
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            iconTint = MaterialTheme.colorScheme.tertiary,
                            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            onClick = onNavigateNotifications
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))


                AnimatedVisibility(
                    visible = animatedItems[3],
                    enter = fadeIn() + slideInVertically(
                        initialOffsetY = { 40 },
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                    ),
                    exit = fadeOut()
                ) {
                    ElevatedButton(
                        onClick = onLogout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.error
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Cerrar sesión"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cerrar Sesión",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ModernAdminCard(
    icon: ImageVector,
    title: String,
    description: String,
    containerColor: Color,
    iconTint: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconTint.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModernAdminDashboard() {
    MaterialTheme {
        AdminDashboard(
            onNavigatePendientes = {},
            onNavigateNotifications = {},
            onLogout = {}
        )
    }
}