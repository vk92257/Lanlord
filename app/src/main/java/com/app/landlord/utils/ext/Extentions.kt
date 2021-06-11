package com.app.landlord.utils.ext

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IntRange
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.landlord.R
import java.util.*


fun View.switchVisibility(goneView: View? = null) {
    if (goneView != null) goneView.visibility = View.GONE
    visibility = View.VISIBLE
}

fun View.onOffVisibility(on: Boolean = true) {
    if (on.not()) visibility = View.GONE else visibility = View.VISIBLE
}

fun View.onOffInvisibility(on: Boolean = true) {
    if (on.not()) visibility = View.INVISIBLE else visibility = View.VISIBLE
}


fun View.converToBitMap(): Bitmap {
    //context: Context, view: View
    val displayMetrics = DisplayMetrics()
    (context as AppCompatActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
    layoutParams =
        ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
    layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
    buildDrawingCache()
    val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    draw(canvas)

    return bitmap
}

inline fun <reified T : Enum<T>> Intent.putExtra(victim: T): Intent =
    putExtra(T::class.qualifiedName, victim.ordinal)

inline fun <reified T : Enum<T>> Intent.getEnumExtra(): T? =
    getIntExtra(T::class.qualifiedName, -1)
        .takeUnless { it == -1 }
        ?.let { T::class.java.enumConstants?.get(it) }

inline fun <reified T : Enum<T>> Bundle.putExtra(victim: T) =
    putInt(T::class.qualifiedName, victim.ordinal)

inline fun <reified T : Enum<T>> Bundle.getEnumExtra(): T? =
    getInt(T::class.qualifiedName, -1)
        .takeUnless { it == -1 }
        ?.let { T::class.java.enumConstants?.get(it) }

fun <T> MutableLiveData<T?>.observeNN(@NonNull owner: LifecycleOwner, obs: (t: T) -> Unit) {
    this.observe(owner, Observer {
        if (it != null) {
            obs(it)
            this.postValue(null)
        }
    })
}


fun String.substringMax(@IntRange(from = 1) strLength: Int): String {
    if (isEmpty()) return this
    if (length < strLength) return this
    return substring(0, strLength)
}

fun TextView.makeClickable(
    text: String,
    start: Int,
    end: Int,
    start2: Int,
    end2: Int,
    action: (View) -> Unit,
    action2: (View) -> Unit
) {
    val spannableString = SpannableString(text)
    val clickable = object : ClickableSpan() {
        override fun onClick(p0: View) {
            action(p0)
        }
    }
    val clickable2 = object : ClickableSpan() {
        override fun onClick(p0: View) {
            action2(p0)
        }
    }
    spannableString.setSpan(clickable, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    spannableString.setSpan(clickable2, start2, end2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

    spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.textColorGrey)), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    spannableString.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, R.color.textColorGrey)), start2, end2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    this.text = spannableString
    setMovementMethod(LinkMovementMethod.getInstance());

}

fun String.openInBrowser(context: Context) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(this)
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


fun String.openInMail(context: Context) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("support@renteeapp.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Rentee App Support")
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun String.toCamelCase(): String {
    if (isEmpty()) return this
    val last = subSequence(1, length).toString()
    val first = this.toCharArray()[0].toString().toUpperCase(Locale.getDefault())
    return first + last.toLowerCase(Locale.getDefault())
}


fun EditText.delayWatcher(delay: Long = 1500, call: (String) -> Runnable) {
    Handler().apply {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0!!.toString().isNotEmpty())
                    postDelayed(call(text.toString()), delay)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
                removeCallbacks(call(text.toString()))
        })
    }
}

fun EditText.addWatcher(call: (String) -> Unit) {
    Handler().apply {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                call(text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })
    }
}


fun EditText.KeyEventSearch(action: (String) -> Unit) {
    setOnKeyListener(object : View.OnKeyListener {
        override fun onKey(
            v: View,
            keyCode: Int,
            event: KeyEvent
        ): Boolean {
            if (event.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                        action(text.toString())
                        return true
                    }
                    else -> {
                    }
                }
            }
            return false
        }
    })

}

