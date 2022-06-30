package com.example.flowtest

import com.example.flowtest.db.AppDatabase
import com.example.flowtest.db.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ConfigRepository(private val database: AppDatabase) {
    suspend fun config(): Config? = database.configDao().getConfig()

    fun configFlow(): Flow<Config?> = database.configDao().getConfigFlow()

    suspend fun insertConfig(config: Config) = withContext(Dispatchers.IO) {
        database.configDao().insertConfig(config)
    }

    suspend fun updateConfig(config: Config) = withContext(Dispatchers.IO) {
        database.configDao().updateConfig(config)
    }

    suspend fun clearConfig() = withContext(Dispatchers.IO) {
        database.configDao().clearConfig()
    }
}
