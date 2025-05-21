package com.example.proyectofinalapps.utils

import com.example.proyectofinalapps.model.Role
import android.content.Context
import androidx.core.content.edit

object SharedPreferencesUtils {

    fun savePreference(context: Context, userId: String, role: Role) {
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("userId", userId)
            putString("role", role.toString())
        }
    }


    fun clearPreference(context: Context) {
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            clear()
        }
    }

    fun getPreference(context: Context): Map<String, Any> {
        val sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "")
        val rol = sharedPreferences.getString("role", "")

        return if (userId.isNullOrEmpty() || rol.isNullOrEmpty()) {
            emptyMap()
        }
        else {
            mapOf(
                "userId" to userId ,
                "role" to rol
            )
        }
    }
}