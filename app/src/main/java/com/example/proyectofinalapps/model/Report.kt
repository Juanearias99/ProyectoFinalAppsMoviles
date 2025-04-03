package com.example.proyectofinalapps.model

import java.util.Date

class Report (
    var idReporte: String,
    var titulo: String,
    var descripcion: String,
    var categoria: String,
    var localizacion: Location,
    var imagenes: List<String>,
    var fechaCreacion: Date,
    var estado: String,
    var idUsuario: User,
    var idComentario: Comment,
    var idNotificacion: Notification
) {
}