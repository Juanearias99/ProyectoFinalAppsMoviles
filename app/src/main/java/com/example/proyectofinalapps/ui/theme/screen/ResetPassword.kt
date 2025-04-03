package com.example.proyectofinalapps.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectofinalapps.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPassword(navigateToLoginScreenP: () -> Unit) {
    var passwordConfirmation by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var errorPassword by rememberSaveable { mutableStateOf(false) }
    var visibilityPassword by remember { mutableStateOf(false) }
    var visibilityPasswordConfirmation by remember { mutableStateOf(false) }
    var context = LocalContext.current

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
                                .clickable { navigateToLoginScreenP() }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        paddingValues
        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.ResetPasswordTitle))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(id = R.string.ResetPassword)) },
                    singleLine = true,
                    isError = errorPassword,
                    visualTransformation = if (visibilityPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image =
                            if (visibilityPassword) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                        IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                            Icon(
                                imageVector = image,
                                contentDescription = stringResource(id = R.string.show_password)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    supportingText = {
                        if (errorPassword) {
                            errorPassword = password.length < 4
                            Text(text = stringResource(id = R.string.validationPassword))
                        }
                    }
                )

                OutlinedTextField(
                    value = passwordConfirmation,
                    onValueChange = { passwordConfirmation = it },
                    label = { Text(text = stringResource(id = R.string.ResetPasswordConfirmation)) },
                    singleLine = true,
                    isError = errorPassword,
                    visualTransformation = if (visibilityPasswordConfirmation) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image =
                            if (visibilityPasswordConfirmation) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                        IconButton(onClick = {
                            visibilityPasswordConfirmation = !visibilityPasswordConfirmation
                        }) {
                            Icon(
                                imageVector = image,
                                contentDescription = stringResource(id = R.string.show_password)
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.8f),
                    supportingText = {
                        if (errorPassword) {
                            errorPassword = passwordConfirmation.length < 4
                            Text(text = stringResource(id = R.string.validationPassword))
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))


                Button(
                    onClick = {
                        if (passwordConfirmation == password) {
                            Toast.makeText(
                                context,
                                context.getString(R.string.login_success),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                context, context.getString(R.string.ConfirmationButtonError),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(48.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text(
                        text = stringResource(id = R.string.ConfirmationButton),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),

                        )
                }
            }
        }
    }
}