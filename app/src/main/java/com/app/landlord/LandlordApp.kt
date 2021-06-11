package com.app.landlord

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.lonoshapp.utils.SessionManager


class LandlordApp : Application() {

    companion object {
        var ON_CLICK_DELAY = 700
        var  lastTimeClicked = 0L
        var sessionManager: SessionManager?=null
        fun session(context: Context): SessionManager {
            if (sessionManager == null)
                sessionManager = SessionManager(context)
            return sessionManager!!
        }
    }

    operator fun get(context: Context): LandlordApp {
        return context.applicationContext as LandlordApp
    }

    override fun onCreate() {
        super.onCreate()
        sessionManager = SessionManager(this)
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
//        FirebaseApp.getInstance()
//        FirebaseApp.initializeApp(this)
//        Places.initialize(applicationContext, getString(R.string.map_key))

    }

}