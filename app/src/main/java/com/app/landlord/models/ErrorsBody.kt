
import android.content.Context
import com.app.landlord.R
import com.google.gson.Gson

import retrofit2.HttpException
import java.nio.charset.Charset


data class ErrorsBody(
    val className: String,
    val code: Int,
    val errors: Errors,
    val message: String,
    val name: String
)

data class Errors(
    val e: String
)

fun Context.errorMessage(error: Throwable): String {
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
