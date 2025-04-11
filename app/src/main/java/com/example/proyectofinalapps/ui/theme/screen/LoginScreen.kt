package com.example.proyectofinalapps.ui.theme.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalapps.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigateToRegister: () -> Unit,
    navigateToHomeUser: () -> Unit,
    navigateToEmailForgotPassword: () -> Unit,
    naviagteToHomeAdmin: () -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var errorEmail by rememberSaveable { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }
    var errorPassword by rememberSaveable { mutableStateOf(false) }
    var visibilityPassword by remember { mutableStateOf(false) }

    var context = LocalContext.current
    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {

            Image(
                painter = painterResource(id = R.drawable.layered_waves_haikei),
                contentDescription = "Fondo con ondas azules",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 0.dp)

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

                //email
                OutlinedTextField(
                    value = email,
                    singleLine = true,
                    isError = errorEmail,
                    supportingText = {
                        if (errorEmail) {
                            Text(text = stringResource(R.string.validationEmail))
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = {
                        Text(text = stringResource(id = R.string.emailLabel))
                    },
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

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.passwordLabel)) },
                    singleLine = true,
                    isError = errorPassword,
                    visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(0.8f),
                    supportingText = {
                        if (errorPassword) {
                            errorPassword = password.length < 4
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
                        //modifier = Modifier.clickable { navigateToEmailForgotPassword() }
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

                Button(
                    onClick = {
                        if (email == "usuario@gmail.com" && password == "1234") {
                            Toast.makeText(
                                context,
                                context.getString(R.string.login_success),
                                Toast.LENGTH_SHORT
                            ).show()
                            navigateToHomeUser()
                        } else if (email == "admin@gmail.com" && password == "12345") {
                            Toast.makeText(
                                context,
                                context.getString(R.string.login_success),
                                Toast.LENGTH_SHORT
                            ).show()
                            naviagteToHomeAdmin()
                        }else {
                            Toast.makeText(
                                context, context.getString(R.string.login_error),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier

                        .fillMaxWidth(0.8f)
                        .height(48.dp),

                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Icono de usuario"
                    )
                    Text(
                        text = stringResource(id = R.string.login_button),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
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
                        modifier = Modifier.clickable { navigateToRegister() }
                    )
                }
            }
        }
    }
}