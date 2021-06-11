package com.app.landlord

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.app.landlord.LandlordApp.Companion.session
import com.app.landlord.databinding.ActivitySplashBinding
import com.app.landlord.modules.MainActivity
import com.app.landlord.modules.intro.IntroActivity
import com.lonoshapp.utils.SessionManager

class SplashActivity : AppCompatActivity() {
    var binding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (binding == null) {
            binding = ActivitySplashBinding.inflate(layoutInflater)
        }
        setContentView(binding!!.root)
        splashTimer()
    }

    private fun splashTimer() {
        Handler().postDelayed({
//            if (session(this).getPrefIsLogin(SessionManager.IS_LOGIN))
                startActivity(Intent(this,  IntroActivity::class.java))



            finishAffinity()
        }, 3000)

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