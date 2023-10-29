package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("roles")
	val roles: List<String>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("accessToken")
	val accessToken: String,

	@field:SerializedName("tokenType")
	val tokenType: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
