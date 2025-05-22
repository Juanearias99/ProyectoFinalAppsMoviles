package com.example.proyectofinalapps.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectofinalapps.model.Location
import com.example.proyectofinalapps.model.Report
import com.example.proyectofinalapps.model.ReportState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime


class ReportsViewModel: ViewModel(){

    private val _reports = MutableStateFlow(emptyList<Report>())
    val reports : StateFlow<List<Report> > = _reports.asStateFlow()

    fun create (report: Report){
     _reports.value += report
    }

    fun delete(report: Report) {
        _reports.value = _reports.value.filter { it.id != report.id }
    }

    fun findById(id: String): Report? {
        return _reports.value.find { it.id == id }
    }

    fun findByUser(userId: String): List<Report> {
        return _reports.value.filter { it.userId == userId }
    }

    fun getReports(): List<Report> = listOf(
        Report("1", "Reporte 1", "Descripcion del reporte 1", ReportState.RESUELTO.toString(), listOf("imagen1.jpg", "imagen2.jpg"), Location(1.0, 2.0), LocalDateTime.now(), "1"),
        Report("2", "Reporte 2", "Descripcion del reporte 2", ReportState.PENDIENTE.toString(), listOf("imagen3.jpg"), Location(3.0, 4.0), LocalDateTime.now(), "2"),
        Report("3", "Reporte 3", "Descripcion del reporte 3", ReportState.PENDIENTE.toString(), listOf("imagen5.jpg"), Location(5.0, 6.0), LocalDateTime.now(), "3"),

        )



}