package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName

data class PowerSupplies(
    @SerializedName("PowerSupplies")
    val powerSupplies: List<PowerSupplie> = emptyList(),
)

data class PowerSupplie (
    @SerializedName("FirmwareVersion")
    val firmwareVersion: String,

    @SerializedName("LastPowerOutputWatts")
    val lastPowerOutputWatts: Long,

    @SerializedName( "LineInputVoltage")
    val lineInputVoltage: Long,

    @SerializedName( "LineInputVoltageType")
    val lineInputVoltageType: String,

    @SerializedName( "Model")
    val model: String,

    @SerializedName( "Name")
    val name: String,

    @SerializedName( "Oem")
    val oem: OEM,

    @SerializedName( "PowerCapacityWatts")
    val powerCapacityWatts: Long,

    @SerializedName( "PowerSupplyType")
    val powerSupplyType: String,

    @SerializedName( "SerialNumber")
    val serialNumber: String,

    @SerializedName( "SparePartNumber")
    val sparePartNumber: String,

    @SerializedName( "Status")
    val status: Status
)


data class HP (
    @SerializedName( "AveragePowerOutputWatts")
    val averagePowerOutputWatts: Long,

    @SerializedName( "BayNumber")
    val bayNumber: Long,

    @SerializedName( "HotplugCapable")
    val hotplugCapable: Boolean,

    @SerializedName( "MaxPowerOutputWatts")
    val maxPowerOutputWatts: Long,

    @SerializedName( "Mismatched")
    val mismatched: Boolean,

    @SerializedName( "PowerSupplyStatus")
    val powerSupplyStatus: PowerSupplyStatus,

    @SerializedName( "Type")
    val type: String,

    val iPDUCapable: Boolean,

    @SerializedName("Location")
    val location : String

)

data class OEM (
    @SerializedName("Hp")
    val hp: HP
)
data class PowerSupplyStatus (
    @SerializedName( "State")
    val state: String
)
