package com.example.proyectofinalapps.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectofinalapps.model.Role
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.proyectofinalapps.model.User
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class UserViewModel: ViewModel() {

    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow< List<User> > = _users.asStateFlow()

    init {
        _users.value = getUsers()
    }

    fun create (user: User) {
        _users.value += user
    }

    fun delete (userId: String) {
        _users.value = _users.value.filter { it.userId != userId }
    }

    fun login (email: String, password: String): User? {
     return _users.value.find{ it.email == email && it.password == password }
    }


    fun findById (userId: String): User? {
    return _users.value.find { it.userId == userId }
    }

    fun getUsers(): List<User> {
        return listOf(
            User(
                UUID.randomUUID().toString(),
                "Oliver",
                "Rojas",
                "Armenia",
                "Calle 123",
                "user@gmail.com",
                Role.CLIENT,
                "123456",
                "123456"
            ),
            User(
                UUID.randomUUID().toString(),
                "Juana",
                "Martínez",
                "Armenia",
                "Av. Bolivar 456",
                "admin@gmail.com",
                Role.ADMIN,
                "654321",
                "654321"
            )
        )
    }
}