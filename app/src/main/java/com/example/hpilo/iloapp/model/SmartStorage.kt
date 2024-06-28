package com.example.hpilo.iloapp.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class SmartStorage(
    @SerializedName("@odata.context")
    val odataContext: String="",

    @SerializedName("@odata.id")
    val odataId: String="",

    @SerializedName("@odata.type")
    val odataType: String="",

    @SerializedName("Description")
    val description: String="",

    @SerializedName("Id")
    val id: String="",

    @SerializedName("Name")
    val name: String="",

    @SerializedName("Model")
    val model: String="",

    @SerializedName("Status")
    val status: Status= Status(health = ""),

    @SerializedName("Type")
    val type: String="",

    @SerializedName("links")
    val links: Links=Links(arrayControllers = Href(""), hostBusAdapters = Href(""), self = Href(""))
)

@Entity(tableName = "status")
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
    val self: Href,

    @SerializedName("Member")
    val member: List<Member> = emptyList(),


    @SerializedName("LogicalDrives")
    val logicalDrives: LogicalDrives=LogicalDrives(""),
    @SerializedName("PhysicalDrives")
    val physicalDrives: PhysicalDrives=PhysicalDrives(""),
    @SerializedName("StorageEnclosures")
    val storageEnclosures: StorageEnclosures= StorageEnclosures(""),
    @SerializedName("UnconfiguredDrives")
    val unconfiguredDrives: UnconfiguredDrives=UnconfiguredDrives(""),

    )


data class LogicalDrives(
    val href: String,
)

data class PhysicalDrives(
    val href: String,
)

data class StorageEnclosures(
    val href: String,
)

data class UnconfiguredDrives(
    val href: String,
)
data class Href(
    @SerializedName("href")
    val href: String
)
