package com.example.proyectofinalapps.ui.theme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun componente_comentarios(
    nombre: String,

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "Evento",
            modifier = Modifier
                .size(69.dp)
                .padding(end = 8.dp)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "USER NAME") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}