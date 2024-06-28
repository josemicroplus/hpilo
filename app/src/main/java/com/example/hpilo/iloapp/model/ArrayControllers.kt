package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName


data class ArrayControllersList (

    @SerializedName("links")
    val links: Links,
    @SerializedName("Name")
    val name: String
)



data class Links2(
    @SerializedName("Member")
    val member: List<Member2>
)
data class Member2 (

    @SerializedName("href" ) var href : String? = null

)