package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName

data class SmartStorageDiskDriveCollection(
    @SerializedName("@odata.context")
    val odataContext: String,

    @SerializedName("@odata.id")
    val odataId: String,

    @SerializedName("@odata.type")
    val odataType: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("MemberType")
    val memberType: String,

    @SerializedName("Members")
    val members: List<Member>,

    @SerializedName("Members@odata.count")
    val membersCount: Int,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Total")
    val total: Int,

    @SerializedName("Type")
    val type: String,

    @SerializedName("links")
    val links: Links
)

data class Member(
    @SerializedName("@odata.id")
    val odataId: String,

    var StateR: SmartStorageDiskDrive
)

