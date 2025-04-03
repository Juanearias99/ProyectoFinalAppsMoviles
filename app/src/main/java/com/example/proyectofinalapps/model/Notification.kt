package com.example.proyectofinalapps.model

import java.util.Date

class Notification (
    var idNotificacion: String,
    var Mensaje: String,
    var fechaCreacion: Date,
    var idUsuario: User,
    var idReporte: Report
){
}