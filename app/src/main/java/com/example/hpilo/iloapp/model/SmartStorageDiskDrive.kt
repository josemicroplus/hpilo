package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName

data class SmartStorageDiskDrive(
    @SerializedName("@odata.context")
    val odataContext: String="",

    @SerializedName("@odata.id")
    val odataId: String="",

    @SerializedName("@odata.type")
    val odataType: String="",

    @SerializedName("BlockSizeBytes")
    val blockSizeBytes: Int=0,

    @SerializedName("CapacityGB")
    val capacityGB: Int=0,

    @SerializedName("CapacityLogicalBlocks")
    val capacityLogicalBlocks: Long=0,

    @SerializedName("CapacityMiB")
    val capacityMiB: Int=0,

    @SerializedName("CarrierApplicationVersion")
    val carrierApplicationVersion: String="",

    @SerializedName("CarrierAuthenticationStatus")
    val carrierAuthenticationStatus: String="",

    @SerializedName("CurrentTemperatureCelsius")
    val currentTemperatureCelsius: Int=0,

    @SerializedName("Description")
    val description: String="",

    @SerializedName("DiskDriveStatusReasons")
    val diskDriveStatusReasons: List<String> = emptyList(),

    @SerializedName("EncryptedDrive")
    val encryptedDrive: Boolean=false,

    @SerializedName("FirmwareVersion")
    val firmwareVersion: FirmwareVersion=FirmwareVersion(CurrentVersion("")),

    @SerializedName("Id")
    val id: String="",

    @SerializedName("InterfaceSpeedMbps")
    val interfaceSpeedMbps: Int=0,

    @SerializedName("InterfaceType")
    val interfaceType: String="",

    @SerializedName("Location")
    val location: String="",

    @SerializedName("LocationFormat")
    val locationFormat: String="",

    @SerializedName("MaximumTemperatureCelsius")
    val maximumTemperatureCelsius: Int=0,

    @SerializedName("MediaType")
    val mediaType: String="",

    @SerializedName("Model")
    val model: String="",

    @SerializedName("Name")
    val name: String="",

    @SerializedName("PowerOnHours")
    val powerOnHours: Any?=0,  // Could be Int or null

    @SerializedName("RotationalSpeedRpm")
    val rotationalSpeedRpm: Int=0,

    @SerializedName("SSDEnduranceUtilizationPercentage")
    val ssdEnduranceUtilizationPercentage: Any?=0,  // Could be Int or null

    @SerializedName("SerialNumber")
    val serialNumber: String="",

    @SerializedName("Status")
    val status: Status=Status(health = "") ,

    @SerializedName("Type")
    val type: String="",

    @SerializedName("links")
    val links: Links=Links(arrayControllers = Href(""), hostBusAdapters = Href(""), self = Href(""))
)

data class FirmwareVersion(
    @SerializedName("Current")
    val current: CurrentVersion
)

data class CurrentVersion(
    @SerializedName("VersionString")
    val versionString: String
)
