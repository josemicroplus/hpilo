package com.hpilo.iloapp.repository

import com.hpilo.iloapp.data.ServerDao
import com.hpilo.iloapp.model.Server
import kotlinx.coroutines.flow.Flow

class ServerRepository(private val serverDao: ServerDao) {
    val allServers: Flow<List<Server>> = serverDao.getAllServers()

    suspend fun insert(server: Server) {
        serverDao.insert(server)
    }

    fun getServerById(serverId: Int): Flow<Server> {
        return serverDao.getServerById(serverId)
    }
}
