package com.example.hpilo.iloapp.network

import com.example.hpilo.iloapp.model.ArrayControllersList
import com.example.hpilo.iloapp.model.PowerSupplies
import com.example.hpilo.iloapp.model.ServerThermalInfo
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
    suspend fun getSmartStorageStatus(@Header("Authorization") auth: String): SmartStorage

    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/")
    suspend fun getArrayControllers(@Header("Authorization") auth: String): ArrayControllersList

    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/{arrayControllerId}")
    suspend fun getArrayControllerStatus(@Header("Authorization") auth: String,
                                         @Path("arrayControllerId") arrayControllerId: String): ArrayControllersList


    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/{arrayControllerId}/DiskDrives")
    suspend fun getArrayControllerStatusDisks(@Header("Authorization") auth: String,
                                              @Path("arrayControllerId") arrayControllerId: String): SmartStorageDiskDriveCollection

    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/{arrayControllerId}/DiskDrives/{arrayDiskId}")
    suspend fun getArrayControllerStatusDisksDrive(@Header("Authorization") auth: String,
                                                   @Path("arrayControllerId") arrayControllerId: String,
                                                   @Path("arrayDiskId") arrayDiskId: String): SmartStorageDiskDrive

    @GET("/redfish/v1/Chassis/1/Thermal")
    suspend fun getServerThermalInfo(@Header("Authorization") auth: String): ServerThermalInfo

    @GET("/redfish/v1/Chassis/1/Power")
    suspend fun getPowerSupplies(@Header("Authorization") auth: String): PowerSupplies



/*
    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/0/LogicalDrives/1/DataDrives/")
    suspend fun getSmartStorageDiskDrives(@Header("Authorization") auth: String): SmartStorageDiskDriveCollection


    @GET("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/0/DiskDrives/{diskDriveId}/")
    suspend fun getSmartStorageDiskDrivesStatus(
        @Header("Authorization") auth: String,
        @Path("diskDriveId") diskDriveId: String
    ): SmartStorageDiskDrive
    */
}