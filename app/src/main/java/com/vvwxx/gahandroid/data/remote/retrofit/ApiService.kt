package com.vvwxx.gahandroid.data.remote.retrofit

import com.vvwxx.gahandroid.data.remote.response.AccountDetailResponse
import com.vvwxx.gahandroid.data.remote.response.LoginResponse
import com.vvwxx.gahandroid.data.remote.response.RegisterResponse
import com.vvwxx.gahandroid.data.remote.response.WebResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginAccount(
        @Field("username") username: String,
        @Field("password") password: String,
    ) : WebResponse<LoginResponse>

    @GET("customer/{id}")
    suspend fun getAccountDetail(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : WebResponse<AccountDetailResponse>

    @FormUrlEncoded
    @POST("register")
    suspend fun registerAccount(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("no_telpon") noTelpon: String,
        @Field("alamat") alamat: String,
        @Field("no_identitas") noIdentitas: String,
    ) : WebResponse<RegisterResponse>
}