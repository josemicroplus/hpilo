package com.hpilo.iloapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servers")
data class Server(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ip: String,
    val username: String,
    val password: String
)