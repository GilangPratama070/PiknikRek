package com.gilangpratama.piknikrek.data.remote.response.detail

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("result")
	val result: DetailResult? = null,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
