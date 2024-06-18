package com.hpilo.iloapp.network

import com.hpilo.iloapp.model.DiskStatus
import com.hpilo.iloapp.model.SystemInfo
import retrofit2.http.GET
import retrofit2.http.Header

interface IloApiService {
    @GET("/redfish/v1/Systems/1")
    suspend fun getSystemInfo(@Header("Authorization") auth: String): SystemInfo

    @GET("/redfish/v1/Systems/1/Storage")
    suspend fun getDiskStatus(@Header("Authorization") auth: String): List<DiskStatus>
}
