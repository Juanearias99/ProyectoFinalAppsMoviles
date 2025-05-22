package com.example.proyectofinalapps.model

import java.time.LocalDateTime
import java.util.Date

class Report (
    var id: String,
    var title: String,
    var description: String,
    var state: String,
    var images: List<String>,
    var location: Location,
    var fecha: LocalDateTime,
    var userId: String
) {
}