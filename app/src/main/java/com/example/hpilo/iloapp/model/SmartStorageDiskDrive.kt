package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName

data class SmartStorageDiskDrive(
    @SerializedName("@odata.context")
    val odataContext: String,

    @SerializedName("@odata.id")
    val odataId: String,

    @SerializedName("@odata.type")
    val odataType: String,

    @SerializedName("BlockSizeBytes")
    val blockSizeBytes: Int,

    @SerializedName("CapacityGB")
    val capacityGB: Int,

    @SerializedName("CapacityLogicalBlocks")
    val capacityLogicalBlocks: Long,

    @SerializedName("CapacityMiB")
    val capacityMiB: Int,

    @SerializedName("CarrierApplicationVersion")
    val carrierApplicationVersion: String,

    @SerializedName("CarrierAuthenticationStatus")
    val carrierAuthenticationStatus: String,

    @SerializedName("CurrentTemperatureCelsius")
    val currentTemperatureCelsius: Int,

    @SerializedName("Description")
    val description: String,

    @SerializedName("DiskDriveStatusReasons")
    val diskDriveStatusReasons: List<String>,

    @SerializedName("EncryptedDrive")
    val encryptedDrive: Boolean,

    @SerializedName("FirmwareVersion")
    val firmwareVersion: FirmwareVersion,

    @SerializedName("Id")
    val id: String,

    @SerializedName("InterfaceSpeedMbps")
    val interfaceSpeedMbps: Int,

    @SerializedName("InterfaceType")
    val interfaceType: String,

    @SerializedName("Location")
    val location: String,

    @SerializedName("LocationFormat")
    val locationFormat: String,

    @SerializedName("MaximumTemperatureCelsius")
    val maximumTemperatureCelsius: Int,

    @SerializedName("MediaType")
    val mediaType: String,

    @SerializedName("Model")
    val model: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("PowerOnHours")
    val powerOnHours: Any?,  // Could be Int or null

    @SerializedName("RotationalSpeedRpm")
    val rotationalSpeedRpm: Int,

    @SerializedName("SSDEnduranceUtilizationPercentage")
    val ssdEnduranceUtilizationPercentage: Any?,  // Could be Int or null

    @SerializedName("SerialNumber")
    val serialNumber: String,

    @SerializedName("Status")
    val status: Status,

    @SerializedName("Type")
    val type: String,

    @SerializedName("links")
    val links: Links
)

data class FirmwareVersion(
    @SerializedName("Current")
    val current: CurrentVersion
)

data class CurrentVersion(
    @SerializedName("VersionString")
    val versionString: String
)
