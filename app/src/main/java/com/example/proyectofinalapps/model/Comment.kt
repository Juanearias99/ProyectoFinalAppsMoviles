package com.example.proyectofinalapps.model

import java.util.Date

class Comment(
    var idComentario: String,
    var contenido: String,
    var fechaCreacion: Date,
    var idUsuario: String,
    var idReporte: String
) {
}