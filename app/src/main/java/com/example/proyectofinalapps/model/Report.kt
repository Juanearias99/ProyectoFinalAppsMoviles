package com.example.proyectofinalapps.model

import java.time.LocalDateTime
import java.util.Date

class Report (
    var idReporte: String,
    var titulo: String,
    var descripcion: String,
    var categoria: String,
    var localizacion: Location,
    var imagenes: List<String> = listOf(),
    var fechaCreacion: LocalDateTime = LocalDateTime.now(),
    var estado: ReportState = ReportState.PENDIENTE,
    var idUsuario: String = "",
    var idComentario: List<Comment> = listOf(),
    var idNotificacion: String = ""
) {
}