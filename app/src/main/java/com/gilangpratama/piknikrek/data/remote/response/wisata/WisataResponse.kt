package com.gilangpratama.piknikrek.data.remote.response.wisata

import com.google.gson.annotations.SerializedName

data class WisataResponse(

	@field:SerializedName("data")
	val data: List<Wisata?>? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
