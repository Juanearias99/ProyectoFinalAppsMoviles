package com.example.proyectofinalapps.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalapps.R
import com.example.proyectofinalapps.model.Role
import com.example.proyectofinalapps.model.User
import com.example.proyectofinalapps.viewmodel.UserViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    usersViewModel: UserViewModel,
    navigateToLoginScreenR: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    val cities = listOf("Armenia", "Pereira", "Manizales", "Medellin")
    var expandedCity by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var errorConfirmPassword by remember { mutableStateOf(false) }
    var visibilityPassword by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = navigateToLoginScreenR) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = Color.Blue
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.register_title),
                    color = Color.Blue,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(22.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Nombre") },
                        leadingIcon = {
                            Icon(
                                Icons.Rounded.Person,
                                contentDescription = null,
                                tint = Color(0xFF1976D2)
                            )
                                      },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Apellido") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = null,
                                tint = Color(0xFF1976D2)
                            )
                                      },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    ExposedDropdownMenuBox(
                        expanded = expandedCity,
                        onExpandedChange = { expandedCity = !expandedCity },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            value = city,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Ciudad") },
                            shape = RoundedCornerShape(12.dp),
                            leadingIcon = { Icon(Icons.Rounded.LocationOn, contentDescription = null, tint = Color(0xFF1976D2)) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCity)
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = expandedCity,
                            onDismissRequest = { expandedCity = false }
                        ) {
                            cities.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        city = item
                                        expandedCity = false
                                    }
                                )
                            }
                        }
                    }

                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Dirección") },
                        leadingIcon = { Icon(Icons.Rounded.Home, contentDescription = null, tint = Color(0xFF1976D2)) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo electrónico") },
                        leadingIcon = { Icon(Icons.Rounded.Email, contentDescription = null, tint = Color(0xFF1976D2)) },
                        shape = RoundedCornerShape(12.dp),
                        isError = errorEmail,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        supportingText = {
                            if (errorEmail) Text("Correo inválido")
                        }
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color(0xFF1976D2)) },
                        trailingIcon = {
                            IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                                Icon(
                                    imageVector = if (visibilityPassword) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(12.dp),
                        isError = errorPassword,
                        modifier = Modifier.fillMaxWidth(),
                        supportingText = {
                            if (errorPassword) Text("Mínimo 4 caracteres")
                        }
                    )

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmar contraseña") },
                        leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = null, tint = Color(0xFF1976D2)) },
                        trailingIcon = {
                            IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                                Icon(
                                    imageVector = if (visibilityPassword) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                        visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        shape = RoundedCornerShape(12.dp),
                        isError = errorConfirmPassword,
                        modifier = Modifier.fillMaxWidth(),
                        supportingText = {
                            if (errorConfirmPassword) Text("Las contraseñas no coinciden")
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        errorEmail = email.isBlank()
                        errorPassword = password.length < 4
                        errorConfirmPassword = confirmPassword != password

                        if (!errorEmail && !errorPassword && !errorConfirmPassword) {
                            usersViewModel.create(
                                User(
                                    userId = UUID.randomUUID().toString(),
                                    name = name,
                                    lastName = lastName,
                                    ciudad = city,
                                    direccion = address,
                                    email = email,
                                    role = Role.CLIENT,
                                    password = password,
                                    confirmPassword = confirmPassword
                                )
                            )
                            Toast.makeText(context, "Usuario Creado exitosamente", Toast.LENGTH_SHORT).show()
                            navigateToLoginScreenR()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PersonAdd,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.registerAccount_button),
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Image(
                    painter = painterResource(id = R.drawable.layered_waves_haikei_reg),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }
        }
    }
}
