package com.app.landlord.modules.signUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.app.landlord.R
import com.app.landlord.databinding.ActivitySignUpBinding
import com.app.landlord.modules.login.LoginActivity
import com.app.landlord.utils.Constants
import com.app.landlord.utils.SignUpConst

class SignUp : AppCompatActivity(), View.OnClickListener{
    var signUpBinding :ActivitySignUpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (signUpBinding == null)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding!!.root)
        performclick()

    }

    private fun performclick() {
        signUpBinding!!.back.setOnClickListener(this)
        signUpBinding!!.btnRegister.setOnClickListener(this)
        signUpBinding!!.signUpTermsAndCondition.setOnClickListener(this)
        signUpBinding!!.signUpAlreadyHaveAccount.setOnClickListener(this)
        signUpBinding!!.signUpPrivacyPolicy.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.back->{
                finish()
            }
            R.id.btnRegister->{
                    verifyInfo()
            }
            R.id.signUpTermsAndCondition->{
              startActivity(Intent(this,TermsAndCondition::class.java)
                  .putExtra(SignUpConst.TERMS_AND_CONDTION,true))
            }
            R.id.signUpAlreadyHaveAccount->{
                        singInActivity()
            }
            R.id.signUpPrivacyPolicy->{
                startActivity(Intent(this,TermsAndCondition::class.java)
                    .putExtra(SignUpConst.RRIVACY_POLICY,true))
            }
        }
    }

    private fun singInActivity() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }


//    private fun showTermsAndCondtion() {
//
//        var mDialogView = LayoutInflater.from(this)
//            .inflate(R.layout.dialog_terms_and_condition, null)
//        //AlertDialogBuilder
//        val mBuilder =  AlertDialog.Builder(this).setView(mDialogView)
//
//        mBuilder?.setCancelable(true)
//        //show dialog
//        val mAlertDialog = mBuilder?.show()
//
//
//
//
//    }

    private fun verifyInfo() {
            if (TextUtils.isEmpty(signUpBinding!!.singUpFullName.text.toString())){
                signUpBinding!!.singUpFullName.setError("Please enter your name")
            }else if(TextUtils.isEmpty(signUpBinding!!.singUpEmail.text.toString())){
                signUpBinding!!.singUpEmail.setError("Please enter your email")
            }else if(TextUtils.isEmpty(signUpBinding!!.signUpPassword.text.toString())){
                signUpBinding!!.signUpPassword.setError("Please enter your password")
            }else if(TextUtils.isEmpty(signUpBinding!!.signUpConfirmPswrd.text.toString())){
                signUpBinding!!.signUpConfirmPswrd.setError("Please enter your confirm password")
            }else{
                singInActivity()
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