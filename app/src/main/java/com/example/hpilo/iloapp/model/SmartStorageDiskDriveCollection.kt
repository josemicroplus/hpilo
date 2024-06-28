package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName

data class SmartStorageDiskDriveCollection(
    @SerializedName("@odata.context")
    val odataContext: String="",

    @SerializedName("@odata.id")
    val odataId: String="",

    @SerializedName("@odata.type")
    val odataType: String="",

    @SerializedName("Description")
    val description: String="",

    @SerializedName("MemberType")
    val memberType: String="",

    @SerializedName("Members")
    val members: List<Member> = emptyList(),

    @SerializedName("Members@odata.count")
    val membersCount: Int=0,

    @SerializedName("Name")
    val name: String="",

    @SerializedName("Total")
    val total: Int=0,

    @SerializedName("Type")
    val type: String="",

    @SerializedName("links")
    val links: Links=Links(arrayControllers = Href(""), hostBusAdapters = Href(""), self = Href(""))
)

data class Member(

    @SerializedName("href")
    val href: String="",

    var StateR: SmartStorageDiskDrive
)

