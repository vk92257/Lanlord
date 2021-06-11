package com.app.landlord.modules.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.app.landlord.R
import com.app.landlord.databinding.ActivityLoginBinding
import com.app.landlord.modules.choiceScreen.ChoiceScreenActivity
import com.app.landlord.modules.forgetPassword.ForgetPasswordActivity
import com.app.landlord.modules.signUp.SignUp

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    var loginViewBinding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (loginViewBinding == null) {

            loginViewBinding = ActivityLoginBinding.inflate(layoutInflater)
        }
        setContentView(loginViewBinding!!.root)
        performOnclick()
    }

    private fun performOnclick() {
        loginViewBinding!!.continueAsGuest.setOnClickListener(this)
        loginViewBinding!!.signIn.setOnClickListener(this)
        loginViewBinding!!.loginAlreadyHaveAccount.setOnClickListener(this)
        loginViewBinding!!.signInForgetPassword.setOnClickListener(this)
        loginViewBinding!!.showPasswordScreen.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.signIn -> {
                verifyInfo()
            }
            R.id.continueAsGuest -> {
                startActivity(Intent(this, ChoiceScreenActivity::class.java))
                finish()
            }
            R.id.loginAlreadyHaveAccount -> {
                startActivity(Intent(this, SignUp::class.java))
                finish()
            }
            R.id.signInForgetPassword -> {
                startActivity(Intent(this, ForgetPasswordActivity::class.java))
            }
           R.id.show_password_screen->{
             hideAndShowPassword()
           }
        }
    }

    private fun hideAndShowPassword() {
        loginViewBinding!!.loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)

//        if ( loginViewBinding!!.loginPassword.inputType == InputType.TEXTP){
//        loginViewBinding!!.loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
//    }else if (loginViewBinding!!.loginPassword.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
//            loginViewBinding!!.loginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
//    }
    }


    private fun verifyInfo() {

        if (TextUtils.isEmpty(loginViewBinding!!.loginEmail.text.toString())) {
            loginViewBinding!!.loginEmail.setError("Please enter your email")
        } else if (TextUtils.isEmpty(loginViewBinding!!.loginPassword.text.toString())) {
            loginViewBinding!!.loginPassword.setError("Please enter your password")
            loginViewBinding!!.loginPassword.requestFocus()
        }

    }
    private var doubleBackToExitPressedOnce: Boolean = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}