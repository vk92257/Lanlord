package com.app.landlord.modules.forgetPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.landlord.R
import com.app.landlord.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : AppCompatActivity(), View.OnClickListener {
        var forgetPswdViewBinding: ActivityForgetPasswordBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (forgetPswdViewBinding == null){
            forgetPswdViewBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        }
        setContentView(R.layout.activity_forget_password)
        performClick()
    }

    private fun performClick() {
        forgetPswdViewBinding!!.close.setOnClickListener(this)
        forgetPswdViewBinding!!.resendEmail.setOnClickListener(this)
        forgetPswdViewBinding!!.subnit.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
       when(p0?.id){
           R.id.close ->{
                finish()
           }
           R.id.resendEmail->{

           }R.id.subnit->{

       }

       }
    }



}