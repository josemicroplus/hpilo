package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName

data class SmartStorage(
    @SerializedName("@odata.context")
    val odataContext: String,

    @SerializedName("@odata.id")
    val odataId: String,

    @SerializedName("@odata.type")
    val odataType: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("Id")
    val id: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Status")
    val status: Status,

    @SerializedName("Type")
    val type: String,

    @SerializedName("links")
    val links: Links
)

data class Status(
    @SerializedName("Health")
    val health: String
)

data class Links(
    @SerializedName("ArrayControllers")
    val arrayControllers: Href,

    @SerializedName("HostBusAdapters")
    val hostBusAdapters: Href,

    @SerializedName("self")
    val self: Href
)

data class Href(
    @SerializedName("href")
    val href: String
)
