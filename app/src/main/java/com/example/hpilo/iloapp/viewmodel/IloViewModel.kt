package com.example.hpilo.iloapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.hpilo.iloapp.data.AppDatabase
import com.example.hpilo.iloapp.model.Server
import com.example.hpilo.iloapp.model.DiskStatus
import com.example.hpilo.iloapp.model.SystemInfo
import com.example.hpilo.iloapp.network.RetrofitClient
import com.example.hpilo.iloapp.repository.ServerRepository
import kotlinx.coroutines.launch

class IloViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ServerRepository

    val allServers: LiveData<List<Server>>
    private val _systemInfo = MutableLiveData<SystemInfo>()
    val systemInfo: LiveData<SystemInfo> = _systemInfo

    private val _diskStatus = MutableLiveData<List<DiskStatus>>()
    val diskStatus: LiveData<List<DiskStatus>> = _diskStatus

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
}
