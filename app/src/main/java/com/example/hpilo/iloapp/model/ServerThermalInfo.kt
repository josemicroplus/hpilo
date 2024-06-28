package com.example.hpilo.iloapp.model

import com.google.gson.annotations.SerializedName
data class ServerThermalInfo(

    @SerializedName("Fans")
    val fans : List<Fan> = emptyList()
)
data class Fan (

    @SerializedName("CurrentReading") val currentReading : Int,
    @SerializedName("FanName") val fanName : String,
    @SerializedName("Oem") val oem : OEM,
    @SerializedName("Status") val status : Status,
    @SerializedName("Units") val units : String
)
data class StatusFan (

    @SerializedName("Health") val health : String,
    @SerializedName("State") val state : String
)
