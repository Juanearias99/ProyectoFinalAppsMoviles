package com.example.proyectofinalapps.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.proyectofinalapps.model.Report
import com.example.proyectofinalapps.utils.RequestResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ReportViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    private val _reports = MutableStateFlow(emptyList<Report>())
    val reports: StateFlow<List<Report>> = _reports.asStateFlow()

    private val _operationResult = MutableStateFlow<RequestResult?>(null)
    val operationResult: StateFlow<RequestResult?> = _operationResult.asStateFlow()

    private val _currentReport = MutableStateFlow<Report?>(null)
    val currentReport: StateFlow<Report?> = _currentReport.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Reportes del usuario actual
    private val _userReports = MutableStateFlow(emptyList<Report>())
    val userReports: StateFlow<List<Report>> = _userReports.asStateFlow()

    init {
        getReports()
    }

    fun createReport(report: Report) {
        viewModelScope.launch {
            _operationResult.value = RequestResult.Loading
            try{
             createFirebase(report)

//            _operationResult.value = runCatching {
                createFirebase(report)
                _operationResult.value = RequestResult.Success("Report created successfully")
                getReports()
//            }.fold
//                onSuccess = { RequestResult.Success("User created successfully") },
//                onFailure = { RequestResult.Failure(it.message ?: "Error creating user") }
            }catch (e: Exception){
                _operationResult.value = RequestResult.Failure(e.message ?:"Error creating Report")
            }
        }
    }


    private suspend fun createFirebase(report: Report) {
        val documentRef = db.collection("reports").document()
        val idReporte = documentRef.id

        val currentUserId = auth.currentUser?.uid ?: ""
        val reportWithId = report.copy(
            idReporte = idReporte,
            idUsuario = if (report.idUsuario.isNullOrEmpty()) currentUserId else report.idUsuario
        )

        documentRef.set(reportWithId).await()
    }


    fun update(report: Report) {
        viewModelScope.launch {
            _operationResult.value = RequestResult.Loading
            try {
                updateFirebase(report)
                _operationResult.value = RequestResult.Success("Reporte actualizado exitosamente")
            } catch (e: Exception) {
                _operationResult.value = RequestResult.Failure(e.message ?: "Error actualizando reporte")
            }
        }
    }

    private suspend fun updateFirebase(report: Report) {
        if (report.idReporte.isEmpty()) {
            throw IllegalArgumentException("ID del reporte no puede estar vacío")
        }

        db.collection("reports")
            .document(report.idReporte)
            .set(report)
            .await()

        // Actualizar la lista local
        getReports()
    }

    fun delete(reportId: String) {
        viewModelScope.launch {
            _operationResult.value = RequestResult.Loading
            try {
                deleteFirebase(reportId)
                _operationResult.value = RequestResult.Success("Reporte eliminado exitosamente")
            } catch (e: Exception) {
                _operationResult.value = RequestResult.Failure(e.message ?: "Error eliminando reporte")
            }
        }
    }

    private suspend fun deleteFirebase(reportId: String) {
        if (reportId.isEmpty()) {
            throw IllegalArgumentException("ID del reporte no puede estar vacío")
        }

        db.collection("reports")
            .document(reportId)
            .delete()
            .await()

        // Actualizar la lista local
        getReports()
    }

    fun findById(reportId: String) {
        viewModelScope.launch {
            try {
                findByIdFirebase(reportId)
            } catch (e: Exception) {
                _operationResult.value = RequestResult.Failure(e.message ?: "Error buscando reporte")
            }
        }
    }

    private suspend fun findByIdFirebase(reportId: String) {
        if (reportId.isEmpty()) {
            _currentReport.value = null
            return
        }

        val querySnapshot = db.collection("reports")
            .document(reportId)
            .get()
            .await()

        val report = querySnapshot.toObject(Report::class.java)?.apply {
            this.idReporte = querySnapshot.id
        }

        _currentReport.value = report
    }

    fun getReports() {
        viewModelScope.launch {
            try {
                _reports.value = findAllFirebase()
            } catch (e: Exception) {
                _operationResult.value = RequestResult.Failure(e.message ?: "Error obteniendo reportes")
            }
        }
    }

    private suspend fun findAllFirebase(): List<Report> {
        val querySnapshot = db.collection("reports")
            .get()
            .await()

        return querySnapshot.documents.mapNotNull { document ->
            document.toObject(Report::class.java)?.apply {
                idReporte = document.id
            }
        }
    }

    // Función para obtener reportes por usuario
    fun getReportsByUser(userId: String? = null) {
        viewModelScope.launch {
            try {
                val userIdToQuery = userId ?: auth.currentUser?.uid ?: ""
                if (userIdToQuery.isNotEmpty()) {
                    _reports.value = findReportsByUserFirebase(userIdToQuery)
                }
            } catch (e: Exception) {
                _operationResult.value = RequestResult.Failure(e.message ?: "Error obteniendo reportes del usuario")
            }
        }
    }

    private suspend fun findReportsByUserFirebase(userId: String): List<Report> {
        val querySnapshot = db.collection("reports")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        return querySnapshot.documents.mapNotNull { document ->
            document.toObject(Report::class.java)?.apply {
                idReporte = document.id
            }
        }
    }

    // Función para obtener reportes por categoría
    fun getReportsByCategory(category: String) {
        viewModelScope.launch {
            try {
                if (category.isNotEmpty()) {
                    _reports.value = findReportsByCategoryFirebase(category)
                }
            } catch (e: Exception) {
                _operationResult.value = RequestResult.Failure(e.message ?: "Error obteniendo reportes por categoría")
            }
        }
    }

    private suspend fun findReportsByCategoryFirebase(category: String): List<Report> {
        val querySnapshot = db.collection("reports")
            .whereEqualTo("category", category)
            .get()
            .await()

        return querySnapshot.documents.mapNotNull { document ->
            document.toObject(Report::class.java)?.apply {
                idReporte = document.id
            }
        }
    }

    // Función para obtener reportes por estado
    fun getReportsByStatus(status: String) {
        viewModelScope.launch {
            try {
                if (status.isNotEmpty()) {
                    _reports.value = findReportsByStatusFirebase(status)
                }
            } catch (e: Exception) {
                _operationResult.value = RequestResult.Failure(e.message ?: "Error obteniendo reportes por estado")
            }
        }
    }

    private suspend fun findReportsByStatusFirebase(status: String): List<Report> {
        val querySnapshot = db.collection("reports")
            .whereEqualTo("status", status)
            .get()
            .await()

        return querySnapshot.documents.mapNotNull { document ->
            document.toObject(Report::class.java)?.apply {
                idReporte = document.id
            }
        }
    }

    fun resetOperationResult() {
        _operationResult.value = null
    }

    fun clearCurrentReport() {
        _currentReport.value = null
    }
}