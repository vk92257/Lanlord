package com.app.landlord.base

import ErrorsBody
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.gson.Gson
import com.app.landlord.R
import com.app.landlord.utils.AppUtils
import com.app.landlord.utils.DialogsUtil
import retrofit2.HttpException
import java.nio.charset.Charset

open class BaseActivity : AppCompatActivity() {
    var mAppUtils: AppUtils? = null
    var mDialogsUtil: DialogsUtil? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)
        mAppUtils = AppUtils(this)
        mDialogsUtil = DialogsUtil(this)

    }


    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    //hide the keyboard on touch of other views except edittext
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus
        if (view != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && view is EditText && !view.javaClass.name.startsWith(
                "android.webkit."
            )
        ) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x = ev.rawX + view.left - scrcoords[0]
            val y = ev.rawY + view.top - scrcoords[1]
            if (x < view.left || x > view.right || y < view.top || y > view.bottom)
            //  mAppUtils.hideSoftKeyboard(window.decorView.rootView)
                hideKeyboard(window.decorView.rootView)
        }
        return super.dispatchTouchEvent(ev)
    }

    //hide keyboard
    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    //adjust font sizes according to already define font size
    open fun adjustFontScale(configuration: Configuration) {
        Log.i("configuration.fontScale", configuration.fontScale.toString())
        configuration.fontScale = 1.0f
        val metrics: DisplayMetrics = resources.displayMetrics
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)

    }

    fun showFragment(tag: String, container: Int, bundle: Bundle? = null) {
        try {
            supportFragmentManager.executePendingTransactions()
        } catch (e: java.lang.Exception) {
        }
        val transaction = supportFragmentManager.beginTransaction()
        val newFragment = createNewFragmentForTag(tag)
        if (!tag.contains("ROOT")) {
            transaction.addToBackStack(null)
            transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
        } else {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        newFragment?.let {
            if (bundle != null) newFragment.arguments = bundle
            transaction.replace(container, newFragment, tag).commitAllowingStateLoss()
        }
    }

    fun showFragmentWithoutTrans(tag: String, container: Int, bundle: Bundle? = null) {
        try {
            supportFragmentManager.executePendingTransactions()
        } catch (e: java.lang.Exception) {
        }
        val transaction = supportFragmentManager.beginTransaction()
        val newFragment = createNewFragmentForTag(tag)
        if (!tag.contains("ROOT")) {
            transaction.addToBackStack(null)
        } else {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        newFragment?.let {
            if (bundle != null) newFragment.arguments = bundle
            transaction.replace(container, newFragment, tag).commitAllowingStateLoss()
        }
    }

    private fun createNewFragmentForTag(tag: String): Fragment? {
        when (tag) {

        }
        return null
    }

    fun errorMessage(error: Throwable): String {
        try {
            val source = (error as HttpException).response()!!.errorBody()!!.source()
            source.request(Long.MAX_VALUE); // request the entire body.
            val buffer = source.buffer();
            // clone buffer before reading from
            val responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"))
            val gson = Gson()
            val err = gson.fromJson<ErrorsBody>(responseBodyString, ErrorsBody::class.java)
            return err.message.replace("_", " ")
        } catch (e: Exception) {
            return getString(R.string.something_went_wrong)
        }
    }


}