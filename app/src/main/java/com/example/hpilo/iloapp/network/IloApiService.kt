package com.example.hpilo.iloapp.network

import com.example.hpilo.iloapp.model.DiskStatus
import com.example.hpilo.iloapp.model.SmartStorage
import com.example.hpilo.iloapp.model.SmartStorageDiskDrive
import com.example.hpilo.iloapp.model.SmartStorageDiskDriveCollection
import com.example.hpilo.iloapp.model.SystemInfo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IloApiService {
    @GET("/redfish/v1/Systems/1")
    suspend fun getSystemInfo(@Header("Authorization") auth: String): SystemInfo

    @GET("/redfish/v1/Systems/1/SmartStorage")
    suspend fun getDiskStatus(@Header("Authorization") auth: String): SmartStorage

    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/0/LogicalDrives/1/DataDrives/")
    suspend fun getSmartStorageDiskDrives(@Header("Authorization") auth: String): SmartStorageDiskDriveCollection


    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/0/DiskDrives/{diskDriveId}/")
    suspend fun getSmartStorageDiskDrivesStatus(
        @Header("Authorization") auth: String,
        @Path("diskDriveId") diskDriveId: String
    ): SmartStorageDiskDrive
}