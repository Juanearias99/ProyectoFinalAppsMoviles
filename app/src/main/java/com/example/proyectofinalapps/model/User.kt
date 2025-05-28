package com.example.proyectofinalapps.model

data class User (
    var userId: String = "",
    var name: String = "",
    var lastName: String = "",
    var ciudad: String = "",
    var direccion: String = "",
    var email: String = "",
    var role: Role = Role.CLIENT,
    var password: String = "",
    var confirmPassword: String = ""


){
}