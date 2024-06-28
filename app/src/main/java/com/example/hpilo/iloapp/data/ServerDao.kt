package com.example.hpilo.iloapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hpilo.iloapp.model.Server
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDao {
    @Insert
    suspend fun insert(server: Server)

    @Query("SELECT * FROM servers")
    fun getAllServers(): Flow<List<Server>>

    @Query("SELECT * FROM servers WHERE id = :serverId")
    fun getServerById(serverId: Int): Flow<Server>

    @Query("DELETE FROM servers WHERE id = :serverId")
    fun delServerById(serverId: Int)
}
