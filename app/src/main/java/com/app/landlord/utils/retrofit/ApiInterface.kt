package com.app.landlord.utils.retrofit

import com.app.landlord.utils.Constants
import com.lonoshapp.models.RegisterModel
import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    //login API
    @Headers("x-api-key: boguskey")
    @FormUrlEncoded
    @POST(Constants.LOGIN)
    fun login(
        @Field("phone") email: String,
        @Field("device_token") password: String,
        @Field("device_type") device_type: String
    ): Observable<RegisterModel>

    //Signup Api
    @Headers("x-api-key: boguskey")
    @FormUrlEncoded
    @POST(Constants.SINGUP)
    fun signup(
        @Field("phone") email: String,
        @Field("dob") dob: String,
        @Field("name") name: String,
        @Field("code") code: String,
        @Field("device_token") password: String,
        @Field("device_type") device_type: String
    ): Observable<RegisterModel>
    //update token
    @GET(Constants.UPDATE_DEVICE_TOKEN_TO_CHILD)
    fun updateDeviceToken(): Observable<RegisterModel>








}