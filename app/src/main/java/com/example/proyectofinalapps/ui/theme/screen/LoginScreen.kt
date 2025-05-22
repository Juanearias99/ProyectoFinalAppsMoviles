package com.example.proyectofinalapps.ui.theme.screens

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalapps.R
import com.example.proyectofinalapps.model.Role
import com.example.proyectofinalapps.ui.theme.navigation.LocalMainViewModel
import com.example.proyectofinalapps.utils.SharedPreferencesUtils
import com.example.proyectofinalapps.viewmodel.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToRegister: () -> Unit,
    navigateToEmailForgotPassword: () -> Unit,
    navigateToHomeScreen: (Role) -> Unit
) {

    val usersViewModel = LocalMainViewModel.current.usersViewModel

    var email by rememberSaveable { mutableStateOf("") }
    var errorEmail by rememberSaveable { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }
    var errorPassword by rememberSaveable { mutableStateOf(false) }
    var visibilityPassword by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.layered_waves_haikei),
                contentDescription = "Fondo con ondas azules",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.login_title),
                    color = Color.Blue,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = stringResource(id = R.string.login_description),
                    color = Color.Gray,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email,
                    singleLine = true,
                    isError = errorEmail,
                    enabled = !isLoading, // Deshabilitar durante carga
                    supportingText = {
                        if (errorEmail) {
                            Text(text = stringResource(R.string.validationEmail))
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = { Text(text = stringResource(id = R.string.emailLabel)) },
                    onValueChange = {
                        email = it
                        errorEmail = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    },
                    shape = RoundedCornerShape(10.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null,
                            tint = Color(0xFF1976D2)
                        )
                    }
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        errorPassword = password.length < 4
                    },
                    label = { Text(text = stringResource(id = R.string.passwordLabel)) },
                    singleLine = true,
                    isError = errorPassword,
                    enabled = !isLoading, // Deshabilitar durante carga
                    visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(0.8f),
                    supportingText = {
                        if (errorPassword) {
                            Text(text = stringResource(id = R.string.validationPassword))
                        }
                    },
                    shape = RoundedCornerShape(10.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFF1976D2)
                        )
                    },
                    trailingIcon = {
                        val image = if (visibilityPassword) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                        IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                            Icon(
                                imageVector = image,
                                contentDescription = stringResource(id = R.string.show_password)
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.validationForgotPassword),
                        color = Color(0xFF007AFF),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            if (!isLoading) {
                                navigateToEmailForgotPassword()
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Button(
                    onClick = {
                        if (!isLoading) {
                            // Validar campos antes de proceder
                            if (email.isBlank() || password.isBlank()) {
                                Toast.makeText(context, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            if (errorEmail || errorPassword) {
                                Toast.makeText(context, "Por favor corrige los errores", Toast.LENGTH_SHORT).show()
                                return@Button
                            }

                            // Iniciar proceso de login asíncrono
                            isLoading = true
                            scope.launch {
                                try {
                                    // Ejecutar login en hilo de fondo
                                    val user = withContext(Dispatchers.IO) {
                                        usersViewModel.login(email, password)
                                    }

                                    // Volver al hilo principal para actualizar UI
                                    if (user == null) {
                                        Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()

                                        // Guardar preferencias en hilo de fondo
                                        withContext(Dispatchers.IO) {
                                            SharedPreferencesUtils.savePreference(context, user.userId, user.role)
                                        }

                                        // Navegar (esto debe ejecutarse en el hilo principal)
                                        navigateToHomeScreen(user.role)
                                    }
                                } catch (e: Exception) {
                                    Log.e("LoginScreen", "Error durante login", e)
                                    Toast.makeText(context, "Error de conexión. Intenta de nuevo.", Toast.LENGTH_SHORT).show()
                                } finally {
                                    isLoading = false
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    enabled = !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Iniciando sesión...",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = "Icono de usuario"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.login_button),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
                    Canvas(modifier = Modifier.size(8.dp)) {
                        drawCircle(color = Color.Gray)
                    }
                    Divider(modifier = Modifier.weight(1f), color = Color.Gray, thickness = 1.dp)
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row {
                    Text(text = stringResource(id = R.string.register_prompt), color = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.register_button),
                        color = Color(0xFF007AFF),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            if (!isLoading) {
                                navigateToRegister()
                            }
                        }
                    )
                }
            }
        }
    }
}