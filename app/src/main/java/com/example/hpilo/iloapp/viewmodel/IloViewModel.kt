package com.example.hpilo.iloapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.hpilo.iloapp.data.AppDatabase
import com.example.hpilo.iloapp.model.Server
import com.example.hpilo.iloapp.model.DiskStatus
import com.example.hpilo.iloapp.model.SmartStorage
import com.example.hpilo.iloapp.model.SmartStorageDiskDrive
import com.example.hpilo.iloapp.model.SmartStorageDiskDriveCollection
import com.example.hpilo.iloapp.model.SystemInfo
import com.example.hpilo.iloapp.network.RetrofitClient
import com.example.hpilo.iloapp.repository.ServerRepository
import kotlinx.coroutines.launch

class IloViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ServerRepository

    val allServers: LiveData<List<Server>>
    private var _systemInfo = MutableLiveData<SystemInfo>()
    val systemInfo: LiveData<SystemInfo> = _systemInfo

    private var _diskStatus = MutableLiveData<SmartStorage>()
    val diskStatus: LiveData<SmartStorage> = _diskStatus

    private var _smartDisksList = MutableLiveData<SmartStorageDiskDriveCollection>()
    val smartDisksList: LiveData<SmartStorageDiskDriveCollection> = _smartDisksList

    private var _diskList = MutableLiveData<List<SmartStorageDiskDrive>>()
    val diskList: LiveData<List<SmartStorageDiskDrive>> = _diskList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        val serverDao = AppDatabase.getDatabase(application).serverDao()
        repository = ServerRepository(serverDao)
        allServers = repository.allServers.asLiveData()
    }

    fun insert(server: Server) = viewModelScope.launch {
        repository.insert(server)
    }

    fun getServerById(serverId: Int): LiveData<Server> {
        return repository.getServerById(serverId).asLiveData()
    }

    fun fetchSystemInfo(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val info = apiService.getSystemInfo(auth)
            _systemInfo.postValue(info)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }
    fun fetchDiskStatus(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val status = apiService.getDiskStatus(auth)
            _diskStatus.postValue(status)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }
    fun fetchSmartDisksList(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val status = apiService.getSmartStorageDiskDrives(auth)
            _smartDisksList.postValue(status)
           /* _smartDisksList.value?.members?.forEachIndexed { index, element ->
                fetchSmartDisksListStatus(baseUrl,auth,element.odataId,index)
            }*/
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }
    fun fetchSmartDisksListStatus(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)

            var musicList: MutableList<SmartStorageDiskDrive> = mutableListOf()

            _smartDisksList.value?.members?.forEachIndexed { index, element ->
                val id = element.odataId
                val vv = apiService.getSmartStorageDiskDrivesStatus(
                    auth,
                    id.replace("/redfish/v1/Systems/1/SmartStorage/ArrayControllers/0/DiskDrives/", "")
                        .replace("/", "")
                )
                musicList.add(vv)
            }

            _diskList.postValue(musicList)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }



}
