package com.giosoft.savemywallet.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.giosoft.savemywallet.R
import com.giosoft.savemywallet.domain.auth.LoginInteractor
import com.giosoft.savemywallet.presentation.base.FullScreenBaseActivity
import com.giosoft.savemywallet.presentation.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : FullScreenBaseActivity() {

    companion object{
        private const val RC_SIGN_IN = 423
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkIfSignIn()
        signInWithProvider()
    }

    private fun checkIfSignIn() {
        val interactor = LoginInteractor()
        if( interactor.isUserLogedIn()){
            val name:String = FirebaseAuth.getInstance().currentUser?.displayName.toString()
            toast(this,"Bienvenido $name")
            this.navigateToMain()
            finish()
        }else{
            toast(this,"Tienes que iniciar sesión. Muchas gracias")
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }//getLayout()

    fun showError(errorMsg:String){
        this.toast(this,errorMsg)
    }//showError()

    fun signInWithProvider() {
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())

        btn_login_with_google.setOnClickListener {
            startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),
                RC_SIGN_IN)
        }//listener

    }//signInWithProviders()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                navigateToMain()
            } else {
                this.showError("Error al logear con google: ${response?.error.toString()}")
            }
        }
    }//onActivityResult()

    fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }//navigateToMain()

}//LoginActivity
