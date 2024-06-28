package com.example.hpilo.iloapp.model

data class SystemInfo(
    val Model: String,
    val BiosVersion: String,
    val SerialNumber: String,
    val Description: String,
    val HostName: String,
    val Status: Status
)
