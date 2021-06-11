package com.app.landlord.modules.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.app.landlord.R
import com.app.landlord.databinding.ActivityIntroBinding
import com.app.landlord.modules.choiceScreen.ChoiceScreenActivity
import com.app.landlord.modules.intro.adapter.IntroViewPagerAdapter
import com.app.landlord.modules.signUp.SignUp
import com.lonoshapp.models.OnBordingModel

class IntroActivity : AppCompatActivity(), View.OnClickListener {
    var intorActivityBinding: ActivityIntroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intorActivityBinding == null) {
            intorActivityBinding = ActivityIntroBinding.inflate(layoutInflater)
        }
        setContentView(intorActivityBinding!!.root)
        settingUpViewPager()
        performOnClick()
    }


    private fun performOnClick() {
        intorActivityBinding!!.skip.setOnClickListener(this)
        intorActivityBinding!!.btnGetStarted.setOnClickListener(this)
        intorActivityBinding!!.continueAsGuest.setOnClickListener(this)

    }


    /**
     * settingUpViewPager is used for setting up the intro screen with a view pager
     * */
    private fun settingUpViewPager() {
        val mList = ArrayList<OnBordingModel>()
        mList.add(
            OnBordingModel(
                R.drawable.ic_add_property, "Add Property ",
                "Amet neque nisl porttitor duis eu. Nulla faucibus vitae pharetra adipiscing ut morbi. In ultrices est amet massa sodales."
            )
        )
        mList.add(
            OnBordingModel(
                R.drawable.ic_chose_tenant, "Choose Tenant",
                "Amet neque nisl porttitor duis eu. Nulla faucibus vitae pharetra adipiscing ut morbi. In ultrices est amet massa sodales."
            )
        )
        mList.add(
            OnBordingModel(
                R.drawable.ic_assign_apartment, "Assign Apartment",
                "Amet neque nisl porttitor duis eu. Nulla faucibus vitae pharetra adipiscing ut morbi. In ultrices est amet massa sodales."
            )
        )

        intorActivityBinding!!.mViewPager.adapter = IntroViewPagerAdapter(mList)
        intorActivityBinding!!.indicator.setViewPager(intorActivityBinding!!.mViewPager)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (intorActivityBinding != null) {
            intorActivityBinding = null
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.skip -> {
                startSignUpProcess()
            }
            R.id.btnGetStarted -> {
                startSignUpProcess()
            }

            R.id.continueAsGuest -> {
                startActivity(Intent(this, ChoiceScreenActivity::class.java))
                finish()
            }
        }
    }

    private fun startSignUpProcess() {
        startActivity(Intent(this, SignUp::class.java))
        finish()
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


