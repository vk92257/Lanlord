package com.app.landlord.utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.text.format.DateFormat
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.text.DateFormatSymbols
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class AppUtils(private val mContext: Context) {

    fun getDateWithMonth(timeStamp: Long): String {
        val nTimeStamp = timeStamp + (7 * 60 * 60 * 1000) + (30 * 60 * 1000)
        val mDate = Date(nTimeStamp)
        val sdf = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
//        sdf.timeZone = TimeZone.getTimeZone("NZST")
        return sdf.format(mDate)
    }

    fun getDateWithMonthnew(timeStamp: Long): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = timeStamp
        calendar.timeZone = TimeZone.getTimeZone("NZST")
        calendar.add(Calendar.HOUR, 12)
        val date = DateFormat.format("dd/MM/yy", calendar).toString()
        return date
    }


    fun getTime(timeStamp: Long): String {
        val nTimeStamp = timeStamp + (7 * 60 * 60 * 1000) + (30 * 60 * 1000)
        val mDate = Date(nTimeStamp)
        val sdf = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        var date = sdf.format(mDate)
        if (date.contains("a.m."))
            date = date.replace("a.m.", "AM")
        else if (date.contains("p.m."))
            date = date.replace("p.m.", "PM")
        return date
    }

    fun getTimeNormal(timeStamp: Long): String {
        val nTimeStamp = timeStamp
        val mDate = Date(nTimeStamp)
        val sdf = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        var date = sdf.format(mDate)

        if (date.contains("a.m."))
            date = date.replace("a.m.", "AM")
        else if (date.contains("p.m."))
            date = date.replace("p.m.", "PM")

        return date
    }

    fun getTimenew(timeStamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        calendar.add(Calendar.HOUR, 13)
        var date = DateFormat.format("hh:mm a", calendar).toString()
        if (date.contains("p.m.") || date.contains("pm"))
            date = date.replace("p.m.", "PM")
        if (date.contains("a.m.") || date.contains("am"))
            date = date.replace("a.m.", "AM")
        return date


    }


    fun getTime1(timeStamp: Long): String {
        val mDate = Date(timeStamp)
        val sdf = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        var date = sdf.format(mDate)
        if (date.contains("a.m."))
            date = date.replace("a.m.", "AM")
        else if (date.contains("p.m."))
            date = date.replace("p.m.", "PM")

        return date
    }


    fun getDate(timeStamp: Long): String {
        val nTimeStamp = timeStamp /*+ (7 * 60 * 60 * 1000) + (30 * 60 * 1000)*/
        val mDate = Date(nTimeStamp)
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(mDate)

    }

    fun getDatelist(timeStamp: Long): String {
        val nTimeStamp = timeStamp /*+ (7 * 60 * 60 * 1000) + (30 * 60 * 1000)*/
        val mDate = Date(nTimeStamp)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(mDate)

    }

    fun getDate1(timeStamp: Long): String {
        val nTimeStamp = timeStamp /*+ (7 * 60 * 60 * 1000) + (30 * 60 * 1000)*/
        val mDate = Date(nTimeStamp)
        val sdf = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(mDate)

    }


    fun getDatetime(timeStamp: Long): String {
        val nTimeStamp = timeStamp + (7 * 60 * 60 * 1000) + (30 * 60 * 1000)
        val mDate = Date(nTimeStamp)
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.ENGLISH)
        var date = sdf.format(mDate)
        if (date.contains("a.m."))
            date = date.replace("a.m.", "AM")
        else if (date.contains("p.m."))
            date = date.replace("p.m.", "PM")


        return date
    }


    fun getTimeStampWithMonth1(date: String): String {
        if (date.isNotEmpty()) {
            val spf = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
            spf.timeZone = TimeZone.getTimeZone("NZST")
            val newDate: Date
            newDate = spf.parse(date)!!
            val cal = Calendar.getInstance()

            try {

                cal.timeInMillis = newDate.time
                return cal.timeInMillis.toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return cal.timeInMillis.toString()
        } else
            return ""

    }


    fun getTimeFromMillies(time: Long): String {
        val sec = time.toDouble().toLong() % 60
        val mintT = time.toDouble().toLong() / 60
        val mint = mintT % 60
        val hoursT = mintT / 60
        val hour = hoursT % 60
        var am_pm = ""
        if (hour > 12)
            am_pm = " PM"
        else if (hour < 12)
            am_pm = " AM"
        else if (hour.toInt() == 12) {
            am_pm = " PM"
        }

        val sdf = SimpleDateFormat("hh:mm:ss")
        val mDate = sdf.parse("$hour:$mint:$sec")
        val sdf2 = SimpleDateFormat("hh:mm")


        var aa = sdf2.format(mDate!!) + am_pm



        return aa


    }


    fun getTimeStampFromTime(date: String): String {
        if (date.isNotEmpty()) {
            val spf = SimpleDateFormat("hh:mm:ss")
            spf.timeZone = TimeZone.getTimeZone("NZST")

            val newDate: Date
            try {
                newDate = spf.parse(date)!!
                val cal = Calendar.getInstance()
//                cal.timeZone = TimeZone.getTimeZone("NZST")
                cal.timeInMillis = newDate.time
                return cal.timeInMillis.toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        } else
            return ""

    }

    fun getTimeStampFromTimeWithoutSeconds(date: String): String {
        if (date.isNotEmpty()) {
            val spf = SimpleDateFormat("hh:mm")
            val newDate: Date
            try {
                newDate = spf.parse(date)!!
                val cal = Calendar.getInstance()
                cal.timeInMillis = newDate.time
                return cal.timeInMillis.toString()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        } else
            return ""

    }






    fun callEvent(mContext: Context, mNumber: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mNumber))
        (mContext as Activity).startActivity(intent)
    }


    fun isInternetConnected(): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun redirectToAppSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val uri = Uri.fromParts("package", mContext.packageName, null)
        intent.data = uri
        mContext.startActivity(intent)
    }

    val isGpsEnabled: Boolean
        get() {
            val manager = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

    fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email.trim { it <= ' ' }).matches()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun hideSoftKeyboard(view: View) {
        val inputMethodManager =
            mContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideSoftKeyboardOut(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }

    fun getLocaleLanguage(): String {//en it
        var lang = "en"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lang = Resources.getSystem().configuration.locales.get(0).language
        } else {
            lang = Resources.getSystem().configuration.locale.language
        }
        if (lang.contains("it", true))
            return "it"
        else
            return "en"
    }

    fun getMonth(month: Int): String {
        return DateFormatSymbols().months[month]
    }


    fun showToast(text: String) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
    }


    fun goToGpsSettings() {
        val callGPSSettingIntent = Intent(
            Settings.ACTION_LOCATION_SOURCE_SETTINGS
        )
        mContext.startActivity(callGPSSettingIntent)
    }

    /**
     * check if user has permissions for the asked permissions
     */
    fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

/*
    fun shareApp(context: Context) {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
            var shareMessage = "\nLet me recommend you this application\n\n"
            shareMessage =
                shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            context.startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            //e.toString();
        }

    }
*/


    fun round(datavalue: Double, places: Int): Double {
        var value = datavalue
        if (places < 0) throw IllegalArgumentException()

        val factor = Math.pow(10.0, places.toDouble()).toLong()
        value = value * factor
        val tmp = Math.round(value)
        return tmp.toDouble() / factor
    }


    fun formatPrice(price: String): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(java.lang.Double.valueOf(price))
    }

    companion object {

        /*    public AppUtils(Context context) {
        this.mContext = context;
    }*/

        /**
         * Description : Check if user is online or not
         *
         * @return true if online else false
         */


        private val EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            EMAIL_PATTERN
        )

    }

    fun convertDateFormat(date: String, from: String, to: String): String {
        val spf = SimpleDateFormat(from, Locale.ENGLISH)
        val newDate: Date
        try {
            newDate = spf.parse(date)!!
            val spf1 = SimpleDateFormat(to, Locale.ENGLISH)
            return spf1.format(newDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }


}

fun String.toCamelCase(): String {
    if (isEmpty()) return this
    val last = subSequence(1, length).toString()
    val first = this.toCharArray()[0].toString().toUpperCase(Locale.getDefault())
    return first + last.toLowerCase(Locale.getDefault())
}

fun String.getSmallName():String {
    if (isEmpty()) return this
    if (this.contains(" ")) {
        val firstName = this.substring(0, 1)
        val lastName1 = this.substringAfter(" ")
        if (lastName1.isNotEmpty()) {
            return firstName + lastName1.substring(0, 1)
        }
    } else {
        val firstName = this.substring(0, 1)
        return firstName
    }
    return this.substring(0, 1)
}






