package com.gilangpratama.piknikrek.data.remote.response.detail

import com.google.gson.annotations.SerializedName

data class DetailResult(

    @field:SerializedName("images")
    val images: List<String?>? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("image_avatar")
    val imageAvatar: String? = null
)
