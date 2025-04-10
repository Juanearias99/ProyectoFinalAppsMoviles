package com.example.proyectofinalapps.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinalapps.R
import com.example.proyectofinalapps.ui.theme.components.componente_comentarios

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comentarios(navigateToMenuNotifications: () -> Unit) {
    var nuevoComentario by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.volver),
                                    modifier = Modifier
                                .clickable { navigateToMenuNotifications() }
                        )
                    }
                }
            )
        }
    ) { paddingValues -> paddingValues
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = (stringResource(R.string.comentarios_title)),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            //OutlinedTextField es un campo de texto que se puede editar
            OutlinedTextField(
                value = nuevoComentario,
                onValueChange = { nuevoComentario = it },
                label = { Text(stringResource(R.string.comentar)) },
                        modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nuevoComentario.isNotEmpty()) {
                        Toast.makeText(context, "Comentario publicado", Toast.LENGTH_SHORT).show()
                        nuevoComentario = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = nuevoComentario.isNotEmpty()
            ) {
                Text(stringResource(R.string.publicar))
            }

            Spacer(modifier = Modifier.height(20.dp))

            repeat(8) {
                componente_comentarios(nombre = "Comentario #${it + 1}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
    }
}