package com.example.proyectofinalapps.model

class User (
    var idUser: String,
    var name: String,
    var lastName: String,
    var ciudad: String,
    var direccion: String,
    var email: String,
    var role: Role,
    var password: String,
    var confirmPassword: String,
    var idReport: Report,
    var idNotification: Notification,
    var idComment: Comment
){
}