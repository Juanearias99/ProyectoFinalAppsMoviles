package com.example.proyectofinalapps.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.proyectofinalapps.model.User
import com.example.proyectofinalapps.utils.RequestResult
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _users = MutableStateFlow(emptyList<User>())
    val users: StateFlow<List<User>> = _users.asStateFlow()
    private val db = Firebase.firestore

    private val _registerResult = MutableStateFlow<RequestResult?>(null)
    val registerResult: StateFlow<RequestResult?> = _registerResult.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()


    init {
       getUsers()
    }

    fun create(user: User) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching {
                createFirebase(user)
            }.fold(
                onSuccess = { RequestResult.Success("User created successfully") },
                onFailure = { RequestResult.Failure(it.message ?: "Error creating user") }
            )
        }
    }

    private suspend fun createFirebase(user: User) {
        val responseUser = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        val firebaseUserId =
            responseUser.user?.uid ?: throw Exception("No se pudo obtener el ID del usuario")

        val userCopy = user.copy(
            userId = firebaseUserId, // ✅ ahora sí coincide
            name = user.name,
            lastName = user.lastName,
            ciudad = user.ciudad,
            direccion = user.direccion,
            email = user.email,
            role = user.role,
            password = "", // por seguridad
            confirmPassword = ""
        )

        db.collection("users")
            .document(firebaseUserId)
            .set(userCopy)
            .await()
    }


    fun delete(userId: String) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching { deleteFirebase(userId) }
                .fold(
                    onSuccess = { RequestResult.Success("User deleted successfully") },
                    onFailure = { RequestResult.Failure(it.message ?: "Error deleting user") }
                )
        }
    }

    private suspend fun deleteFirebase(userId: String) {
        db.collection("users")
            .document(userId)
            .delete()
            .await()
    }

    fun update(userId: User) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching { updateFirebase(userId) }
                .fold(
                    onSuccess = { RequestResult.Success("User updated successfully") },
                    onFailure = { RequestResult.Failure(it.message ?: "Error updating user") }
                )
        }
    }

    private suspend fun updateFirebase(user: User) {
        db.collection("users")
            .document(user.userId)
            .set(user)
            .await()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _registerResult.value = RequestResult.Loading
            _registerResult.value = runCatching { loginFirebase(email, password) }
                .fold(
                    onSuccess = { RequestResult.Success("User logged successfully") },
                    onFailure = { RequestResult.Failure(it.message ?: "Error logging user") }
                )
        }
    }

    private suspend fun loginFirebase(email: String, password: String) {
        val responseUser = auth.signInWithEmailAndPassword(email, password).await()
        val userId = responseUser.user?.uid ?: ""
        findByIdFirebase(userId)
    }


    fun findById(userId: String) {
        viewModelScope.launch {
            findByIdFirebase(userId)
        }
    }

    private suspend fun findByIdFirebase(userId: String) {
        val querySnapshot = db.collection("users")
            .document(userId)
            .get()
            .await()

        val user = querySnapshot.toObject(User::class.java)?.apply {
            val id = querySnapshot.id
        }

        _currentUser.value = user
    }

    fun logout() {
        auth.signOut()
        _currentUser.value = null
    }

    fun getNameById(userId: String): String {
        val user = _users.value.find { it.userId == userId }
        return user?.name ?: ""
    }

    fun resetRegisterResult() {
        _registerResult.value = null
    }

    fun getUsers() {
        viewModelScope.launch {
            _users.value = findAllFirebase()
        }
    }

    private suspend fun findAllFirebase(): List<User> {
        val querySnapshot = db.collection("users")
            .get()
            .await()

        return querySnapshot.documents.mapNotNull {
            it.toObject(User::class.java)?.apply {
                userId = it.id
            }
        }
    }
}


