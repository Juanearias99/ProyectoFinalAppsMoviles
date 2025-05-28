
package com.example.proyectofinalapps.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalapps.R
import com.example.proyectofinalapps.ui.theme.components.TextFieldForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsReportScreen(
    navigateToHomeUser: () -> Unit,
    navigateToComentarios: () -> Unit,
    navigateToVerificationImport: () -> Unit,
    navigateToVerificationResult: () -> Unit
) {
    var nameReport by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val categories = listOf("Rojo", "Amarillo", "Verde", "Morado")
    var expandedCategory by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf("") }
    var ubication by remember { mutableStateOf("") }
    val context = LocalContext.current

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
                                .clickable { navigateToHomeUser() }
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
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.details_report_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldForm(
                value = nameReport,
                onValueChange = { nameReport = it },
                supportingText = stringResource(id = R.string.details_report_validation),
                label = stringResource(id = R.string.name_report_label),
                onValidate = { nameReport.isBlank() },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expandedCategory,
                onExpandedChange = { expandedCategory = !expandedCategory },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    value = category,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = { Text(text = stringResource(id = R.string.category_label)) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory) }
                )
                ExposedDropdownMenu(
                    expanded = expandedCategory,
                    onDismissRequest = { expandedCategory = false },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    categories.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                category = item
                                expandedCategory = false
                            },
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))



            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(id = R.string.description_label)) },
                modifier = Modifier.fillMaxWidth(0.8f)

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = ubication,
                onValueChange = { ubication = it },
                label = { Text(stringResource(id = R.string.ubication_label)) },
                modifier = Modifier.fillMaxWidth(0.8f)

            )

            Spacer(modifier = Modifier.height(16.dp))

            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = "Subir Imagen",
                modifier = Modifier.size(125.dp)


            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navigateToComentarios()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0061FF), // Azul
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(45.dp),
                shape = RoundedCornerShape(8.dp),

                ) {
                Text(
                    text = "Comentarios",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navigateToVerificationResult()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0061FF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(45.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = "Resueltos",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navigateToVerificationImport()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0061FF),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(45.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = "Importantes",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}




