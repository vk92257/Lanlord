package com.app.landlord.modules.choiceScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.app.landlord.R
import com.app.landlord.databinding.ActivityChoiceScreenBinding
import com.app.landlord.modules.home.HomeActivity

class ChoiceScreenActivity : AppCompatActivity(), View.OnClickListener {

    var choiceBinding : ActivityChoiceScreenBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (choiceBinding == null){
            choiceBinding = ActivityChoiceScreenBinding.inflate(layoutInflater)
        }
        setContentView(choiceBinding!!.root)
        performClicks()
    }

    private fun performClicks() {
        choiceBinding!!.choiceProperty.setOnClickListener(this)
        choiceBinding!!.choiceRender.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
       when(p0?.id){
           R.id.choiceProperty ->{
                startActivity(Intent(this,HomeActivity::class.java))
               finish()
           }
           R.id.choiceRender ->{
               startActivity(Intent(this,HomeActivity::class.java))
               finish()
           }
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