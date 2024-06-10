package com.example.tourguideapp.data.repository

import com.example.tourguideapp.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    fun signIn(email: String, password: String) : Task<AuthResult>
    fun signUp(email: String, password: String) : Task<AuthResult>
    fun currentUser(): FirebaseUser?
    fun addUser(user: User)
}