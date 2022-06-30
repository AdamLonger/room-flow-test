package com.example.flowtest

import com.example.flowtest.db.AppDatabase
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.create(get()) }
    single { ConfigRepository(get()) }
}
