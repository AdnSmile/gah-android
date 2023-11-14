package com.vvwxx.gahandroid.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("account")
	val account: Account,

	@field:SerializedName("token")
	val token: String
)

data class Account(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("id_account")
	val idAccount: Int,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("id_customer")
	val idCustomer: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
