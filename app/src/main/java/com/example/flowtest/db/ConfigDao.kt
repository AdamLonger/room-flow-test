package com.example.flowtest.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigDao {
    @Query("SELECT * FROM config WHERE uid = 1")
    suspend fun getConfig(): Config?

    @Query("SELECT * FROM config WHERE uid = 1")
    fun getConfigFlow(): Flow<Config?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: Config)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateConfig(config: Config)

    @Query("DELETE FROM config")
    suspend fun clearConfig()
}
