package com.lonoshapp.utils

import android.content.Context
import com.app.landlord.R


class SessionManager(val context: Context) {
    companion object {
        val USER_ID = "USER_ID"
        val IS_LOGIN = "IS_LOGIN"
        val USER_TOKEN = "USER_TOKEN"
        val USER_NAME = "USER_NAME"
        val USER_PROFILE = "USER_PROFILE"
        val USER_DATA = "USER_DATA"
        val USER_AGE = "USER_AGE"
        val USER_LNG = "USER_LNG"
        val DEVICE_TOKEN = "DEVICE_TOKEN"
        val ONLINE_STATUS = "ONLINE_STATUS"

    }

    val pref =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun savePrefInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    fun getPrefInt(key: String) = pref.getInt(key, 0)

    fun savePrefString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun getPrefString(key: String) = pref.getString(key, getdefaultValue(key))

    private fun getdefaultValue(key: String): String {
        return when (key) {
            USER_DATA -> ""
            else -> ""
        }
    }

    fun savePrefBool(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun getPrefBool(key: String) = pref.getBoolean(key, false)

    fun savePrefIsLogin(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }


    fun getPrefIsLogin(key: String) = pref.getBoolean(key, false)

    fun clearPrefs() {
        val editor = pref.edit()
        editor.remove(USER_ID)
        editor.remove(IS_LOGIN)
        editor.remove(USER_TOKEN)
        editor.remove(USER_NAME)
        editor.remove(USER_PROFILE)
        editor.remove(USER_DATA)
        editor.remove(USER_AGE)
        editor.apply()
    }

/*
    fun getUserData(mPrefs: SessionManager): RegisterData? {
        try {
            val gson = Gson()
            return gson.fromJson(
                mPrefs.getPrefString(USER_DATA),
                RegisterData::class.java
            )
        } catch (e: Exception) {
            return null
        }
    }
*/


}