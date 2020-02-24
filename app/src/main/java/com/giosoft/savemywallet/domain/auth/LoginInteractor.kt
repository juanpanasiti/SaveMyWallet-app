package com.giosoft.savemywallet.domain.auth

import com.google.firebase.auth.FirebaseAuth

class LoginInteractor {
    fun isUserLogedIn():Boolean{
        return FirebaseAuth.getInstance().currentUser != null
    }
}