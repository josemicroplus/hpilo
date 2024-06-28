package com.example.hpilo.iloapp.viewmodel

import android.app.Application
import android.health.connect.datatypes.units.Power
import android.util.Base64
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hpilo.iloapp.data.AppDatabase
import com.example.hpilo.iloapp.model.ArrayControllersList
import com.example.hpilo.iloapp.model.PowerSupplies
import com.example.hpilo.iloapp.model.Server
import com.example.hpilo.iloapp.model.ServerThermalInfo
import com.example.hpilo.iloapp.model.SmartStorage
import com.example.hpilo.iloapp.model.SmartStorageDiskDrive
import com.example.hpilo.iloapp.model.SmartStorageDiskDriveCollection
import com.example.hpilo.iloapp.model.Status
import com.example.hpilo.iloapp.model.SystemInfo
import com.example.hpilo.iloapp.network.RetrofitClient
import com.example.hpilo.iloapp.repository.ServerRepository
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class IloViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ServerRepository

    //var allServers: LiveData<List<Server>>

    private var _allServers = MutableLiveData<List<Server>>()
    val allServers: LiveData<List<Server>> = _allServers


    private var _arrayControllers = MutableLiveData<ArrayControllersList>()
    val arrayControllers: LiveData<ArrayControllersList> = _arrayControllers



    private var _arrayControllersStatus = MutableLiveData<List<ArrayControllersList>>()
    val arrayControllersStatus: LiveData<List<ArrayControllersList>> = _arrayControllersStatus




    private var _systemInfo = MutableLiveData<SystemInfo>()
    val systemInfo: LiveData<SystemInfo> = _systemInfo

    private var _systemThermalInfo = MutableLiveData<ServerThermalInfo>()
    val systemThermalInfo: LiveData<ServerThermalInfo> = _systemThermalInfo

    private var _systemPowerSupplies = MutableLiveData<PowerSupplies>()
    val systemPowerSupplies: LiveData<PowerSupplies> = _systemPowerSupplies

    private var _SmartStorageStatus = MutableLiveData<SmartStorage>()
    val SmartStorageStatus: LiveData<SmartStorage> = _SmartStorageStatus

    private var _smartDisksList = MutableLiveData<SmartStorageDiskDriveCollection>()
    val smartDisksList: LiveData<SmartStorageDiskDriveCollection> = _smartDisksList

    private var _diskList = MutableLiveData<List<SmartStorageDiskDrive>>()
    val diskList: LiveData<List<SmartStorageDiskDrive>> = _diskList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        val serverDao = AppDatabase.getDatabase(application).serverDao()
        repository = ServerRepository(serverDao)

    }

    fun insert(server: Server) = viewModelScope.launch {
        repository.insert(server)
    }

    fun delete(server: Server) = viewModelScope.launch {
        repository.delete(server)
    }

    fun getServerById(serverId: Int): LiveData<Server> {
        return repository.getServerById(serverId).asLiveData()
    }

    fun getServerThermalInfo(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val info = apiService.getServerThermalInfo(auth)
            _systemThermalInfo.postValue(info)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }
    fun getPowerSupplies(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val info = apiService.getPowerSupplies(auth)
            _systemPowerSupplies.postValue(info)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
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

    fun getAllArrayControllers(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val status = apiService.getArrayControllers(auth)
            _arrayControllers.postValue(status)
            getArrayControllerStatus(baseUrl,auth,status)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }

    fun getArrayControllerStatus(baseUrl: String, auth: String,arr: ArrayControllersList ) = viewModelScope.launch {
        try {
            var musicList: MutableList<ArrayControllersList> = mutableListOf()
            var id=""
            arr.links.member.forEachIndexed { index, element ->
                val apiService = RetrofitClient.create(baseUrl)

                var sss=element.href.toString();
                 id= sss.split("/")[sss.split("/").size-2]

                val status = apiService.getArrayControllerStatus(auth,id)
                musicList.add(status)
            }
            _arrayControllersStatus.postValue(musicList)
            getArrayControllerStatusDisks(baseUrl,auth,id)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }

    fun getArrayControllerStatusDisks(baseUrl: String, auth: String,idArrayController:String ) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val status = apiService.getArrayControllerStatusDisks(auth,idArrayController)
            _smartDisksList.postValue(status)
            getArrayControllerStatusDisksDrive(baseUrl,auth,idArrayController,status)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }
    fun getArrayControllerStatusDisksDrive(baseUrl: String, auth: String,idArrayController:String,ob:SmartStorageDiskDriveCollection ) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)

            var musicList: MutableList<SmartStorageDiskDrive> = mutableListOf()

            ob.links.member.forEachIndexed { index, element ->

                var sss=element.href.toString();
                var arrayDiskId= sss.split("/")[sss.split("/").size-2]

                val status = apiService.getArrayControllerStatusDisksDrive(auth,idArrayController,arrayDiskId)
                musicList.add(status)
            }
            _diskList.postValue(musicList)

        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }



    fun fetchSmartStorageStatus(baseUrl: String, auth: String) = viewModelScope.launch {
        try {
            val apiService = RetrofitClient.create(baseUrl)
            val status = apiService.getSmartStorageStatus(auth)
            _SmartStorageStatus.postValue(status)
        } catch (e: Exception) {
            _error.postValue(e.message)
        }
    }

    /*
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
        }*/
    fun clearInfo() = viewModelScope.launch {
        _systemInfo.postValue(SystemInfo(Model = "", HostName = "", SerialNumber = "", BiosVersion = "", Description = "", Status = Status(health = "")))

        val v:SmartStorage=SmartStorage()
        _SmartStorageStatus.postValue(v)

        _error.postValue("")

        val vv:SmartStorageDiskDriveCollection=SmartStorageDiskDriveCollection()
        _smartDisksList.postValue(vv)
        val vvv:List<SmartStorageDiskDrive> = emptyList()
        _diskList.postValue(vvv)
        val vvvb:ServerThermalInfo = ServerThermalInfo()
        _systemThermalInfo.postValue(vvvb)
        val vvvb2:PowerSupplies = PowerSupplies()
        _systemPowerSupplies.postValue(vvvb2)
    }
    fun getAllServers() = viewModelScope.launch {


        repository.allServers.collect { server ->
            var musicList: MutableList<Server> = mutableListOf()

                server.forEachIndexed { index, element ->
                    var sToAdd: Server = Server(
                        id = element.id,
                        ip = element.ip,
                        username = element.username,
                        password = element.password,
                        name = element.name,
                        status = "Waiting"
                    )
//                sToAdd.status=sInfo.Status.health
                    musicList.add(sToAdd)
                }

            _allServers.postValue(musicList)
            musicList = mutableListOf()
            server.forEachIndexed { index, element ->
                try
                {

                    val auth = "Basic " + Base64.encodeToString(
                        "${element.username}:${element.password}".toByteArray(),
                        Base64.NO_WRAP
                    )
                    val baseUrl = "https://${element.ip}"

                    val apiService = RetrofitClient.create(baseUrl)
                    val sInfo= apiService.getSystemInfo(auth)
                    //  element.status=sInfo.Status
                    var sToAdd: Server=Server(id=element.id,ip=element.ip, username = element.username, password = element.password, name=element.name,status = sInfo.Status.health.toString())
//                sToAdd.status=sInfo.Status.health
                    musicList.add(sToAdd)
                }catch (e: Exception) {
                    var sToAdd: Server=Server(id=element.id,ip=element.ip, username = element.username, password = element.password,name=element.name, status = "Connection Error")
//                sToAdd.status=sInfo.Status.health
                    musicList.add(sToAdd)
                }
            }
            _allServers.postValue(musicList)

        }

    }


}
