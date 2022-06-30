package com.example.flowtest

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.flowtest.db.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class ExampleTest: KoinTest {
    @Before
    fun before() {
        stopKoin()
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(appModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun dummyTest2() = runBlocking {
        val configRepo by inject<ConfigRepository>()
        configRepo.clearConfig()
        assert(configRepo.config() == null)

        configRepo.insertConfig(Config("P1", "S1"))
        val firstEmission = configRepo.configFlow().first()

        configRepo.updateConfig(Config("P2", "S1"))
        val secondEmission =  configRepo.configFlow().first()

        assert(secondEmission?.primaryValue != firstEmission?.primaryValue)
        assert(secondEmission?.secondaryValue == firstEmission?.secondaryValue)
    }

    @Test
    fun dummyTest() = runBlocking {
        val configRepo by inject<ConfigRepository>()
        configRepo.clearConfig()
        assert(configRepo.config() == null)

        val emissions = configRepo.configFlow().filterNotNull().receiver().start()

        configRepo.insertConfig(Config("P1", "S1"))
        val firstEmission = emissions.next()

        configRepo.updateConfig(Config("P2", "S1"))
        val secondEmission = emissions.next()

        assert(secondEmission.primaryValue != firstEmission.primaryValue)
        assert(secondEmission.secondaryValue == firstEmission.secondaryValue)
    }
}
