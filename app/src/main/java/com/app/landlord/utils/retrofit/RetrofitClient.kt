package com.app.landlord.utils.retrofit

import android.annotation.SuppressLint
import android.content.Context
import com.app.landlord.LandlordApp.Companion.session
import com.app.landlord.utils.Constants
import com.lonoshapp.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class RetrofitClient {
    companion object {
        var retrofilt: Retrofit? = null

        fun getRetrofitClient(context: Context): ApiInterface {
//            if (retrofilt == null) {
            val client = getUnsafeOkHttpClient(context)
            retrofilt =
                Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
//            }
            return retrofilt!!.create(ApiInterface::class.java)
        }


        fun getUnsafeOkHttpClient(context: Context): OkHttpClient {

            try {
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }


                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }
                })

                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                val sslSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.followRedirects(false)
                builder.followSslRedirects(false)
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)

                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.apply { this.level = HttpLoggingInterceptor.Level.BODY }
                builder.addInterceptor(getHeadersForApis(context))
                builder.addInterceptor(loggingInterceptor)

//                builder.addInterceptor { chain ->
//                    var request = chain.request()
////                    if (getLoginStatus() != null) {
//                    if (session(context).getPrefIsLogin(SessionManager.IS_LOGIN)) {
//                        request = request.newBuilder()
//                            .addHeader("x-api-key", session(context).getPrefString(SessionManager.USER_TOKEN)!!)
//                            .build()
//                    }
//
//                    chain.proceed(request)
//                }

                /*if (BuildConfig.DEBUG) {
                builder.interceptors().add(loggingInterceptor)
            }*/
                return builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        private fun getHeadersForApis(context: Context): Interceptor {
            return Interceptor { chain ->
                var request = chain.request()
                if (session(context).getPrefIsLogin(SessionManager.IS_LOGIN)) {
                    request = request.newBuilder().addHeader(
                        "x-api-key",
                        session(context).getPrefString(SessionManager.USER_TOKEN)!!
                    )
                        .build()
                }
                chain.proceed(request)
            }
        }

/*
        val unsafeOkHttpClient: OkHttpClient
            get() {
                try {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    val httpClient = OkHttpClient.Builder()
                    httpClient.interceptors().add(getHeadersForApis())
                    httpClient.interceptors().add(httpLoggingInterceptor)
                    httpClient.readTimeout(90, TimeUnit.SECONDS)
                    httpClient.connectTimeout(90, TimeUnit.SECONDS)
                    return httpClient.build()
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }
*/
    }
}