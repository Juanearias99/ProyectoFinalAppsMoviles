package com.example.proyectofinalapps.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectofinalapps.model.Location
import com.example.proyectofinalapps.model.Report
import com.example.proyectofinalapps.model.ReportState
import com.example.proyectofinalapps.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

class ReportViewModel: ViewModel() {

    private val _reports = MutableStateFlow(emptyList<Report>())
    val reports: StateFlow<List<Report>> = _reports.asStateFlow()

    init {
        _reports.value = getReports()
    }

    fun create(report: Report) {
        _reports.value += report
    }

    fun delete(reportId: String) {
        _reports.value = _reports.value.filter { it.idReporte == reportId }
    }

    fun findById(reportId: String): Report? {
        return _reports.value.find { it.idReporte == reportId }
    }

    fun findByUserId(userId: String): List<Report> {
        return _reports.value.filter { it.idUsuario == userId }
    }

    fun getReports(): List<Report> {
        return listOf(
            Report("1", "Reporte 1", "Descripcion Reporte 1", "Robo", Location(4.558388, -75.661660), listOf("https://www.itmplatform.com/es/blog/todo-lo-que-debes-incluir-en-un-reporte-de-estado-de-proyecto/"),
                 LocalDateTime.now(), ReportState.RESUELTO, "2342", listOf(), "890"))
            Report("1", "Reporte 2", "Descripcion Reporte 2", "Atraco", Location(4.558388, -75.661660), listOf("https://www.itmplatform.com/es/blog/todo-lo-que-debes-incluir-en-un-reporte-de-estado-de-proyecto/"),
                LocalDateTime.now(), ReportState.RESUELTO, "2346", listOf(), "890")
            Report("1", "Reporte 3", "Descripcion Reporte 3", "Carcel", Location(4.558388, -75.661660), listOf("https://www.itmplatform.com/es/blog/todo-lo-que-debes-incluir-en-un-reporte-de-estado-de-proyecto/"),
                LocalDateTime.now(), ReportState.RESUELTO, "2345", listOf(), "890")
            Report("1", "Reporte 4", "Descripcion Reporte 4", "Matanza", Location(4.558388, -75.661660), listOf("https://www.itmplatform.com/es/blog/todo-lo-que-debes-incluir-en-un-reporte-de-estado-de-proyecto/"),
                LocalDateTime.now(), ReportState.RESUELTO, "2344", listOf(), "890")
            Report("1", "Reporte 5", "Descripcion Reporte 5", "Matoneo", Location(4.558388, -75.661660), listOf("https://www.itmplatform.com/es/blog/todo-lo-que-debes-incluir-en-un-reporte-de-estado-de-proyecto/"),
                LocalDateTime.now(), ReportState.RESUELTO, "2654", listOf(), "1100")


    }
}