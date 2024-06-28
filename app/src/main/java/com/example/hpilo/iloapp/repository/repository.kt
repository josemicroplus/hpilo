package com.example.hpilo.iloapp.repository

import com.example.hpilo.iloapp.data.ServerDao
import com.example.hpilo.iloapp.model.Server
import kotlinx.coroutines.flow.Flow

class ServerRepository(private val serverDao: ServerDao) {
    val allServers: Flow<List<Server>> = serverDao.getAllServers()

    suspend fun insert(server: Server) {
        serverDao.insert(server)
    }
    suspend fun delete(server: Server) {
        serverDao.delServerById(server.id)
    }

    fun getServerById(serverId: Int): Flow<Server> {
        return serverDao.getServerById(serverId)
    }
}
