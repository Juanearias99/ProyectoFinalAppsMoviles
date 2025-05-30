package com.example.proyectofinalapps.ui.theme.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.proyectofinalapps.ui.theme.components.AlertMessage
import com.example.proyectofinalapps.ui.theme.components.AlertType
import com.example.proyectofinalapps.ui.theme.components.TextFieldForm
import com.example.proyectofinalapps.utils.RequestResult
import com.example.proyectofinalapps.viewmodel.ReportViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReportScreen(
    reportViewModel: ReportViewModel,
    navigateToHomeUser: () -> Unit
) {

    var nameReport by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val categories = listOf("Rojo", "Amarillo", "Verde", "Morado")
    var expandedCategory by remember { mutableStateOf(false) }
    var description by remember { mutableStateOf("") }
    var ubication by remember { mutableStateOf("") }
    var selectedFileName by remember { mutableStateOf("Subir Archivo") }
    val operationResult by reportViewModel.operationResult.collectAsState()
    val isLoading by reportViewModel.isLoading.collectAsState()
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
                text = stringResource(R.string.new_report_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldForm(
                value = nameReport,
                onValueChange = { nameReport = it },
                supportingText = stringResource(R.string.name_report_error),
                label = stringResource(R.string.name_report_label),
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
                    placeholder = { Text(text = stringResource(R.string.category_label)) },
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
                label = { Text(stringResource(R.string.description_label)) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = ubication,
                onValueChange = { ubication = it },
                label = { Text(stringResource(R.string.ubication_label)) },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let {
                    selectedFileName = it.lastPathSegment ?: context.getString(R.string.upload_file_description)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { launcher.launch("*/*") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(55.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CloudUpload,
                    contentDescription = stringResource(R.string.upload_file)
                )

                Text(selectedFileName)
            }

            Spacer(modifier = Modifier.height(50.dp))


            when (operationResult) {
                null -> {
                }

                is RequestResult.Success -> {

                    AlertMessage(
                        type = AlertType.SUCESS,
                        message = (operationResult as RequestResult.Success).message
                    )
                    LaunchedEffect(Unit) {
                        delay(3000)
                        reportViewModel.resetOperationResult()
                        navigateToHomeUser()
                    }

                }

                is RequestResult.Failure -> {
                    AlertMessage(
                        type = AlertType.ERROR,
                        message = (operationResult as RequestResult.Failure).errorMessage
                    )

                    LaunchedEffect(Unit) {
                        delay(3000)
                        reportViewModel.resetOperationResult()

                    }
                }

                is RequestResult.Loading -> {
                    LinearProgressIndicator()
                }
            }

            Button(
                onClick = { },
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
                    text = stringResource(R.string.publish_button),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
