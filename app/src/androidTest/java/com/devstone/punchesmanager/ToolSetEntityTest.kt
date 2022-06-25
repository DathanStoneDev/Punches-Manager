package com.devstone.punchesmanager

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devstone.punchesmanager.data.AppDatabase
import com.devstone.punchesmanager.data.dao.ToolSetDao
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToolSetEntityTest {

    private lateinit var toolSetDao: ToolSetDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        toolSetDao = db.toolSetDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    /*@Test
    fun insertToolSetTest() = runBlocking {
        val toolSet = ToolSet(
            "00000", ToolTip.DOUBLE, 30,
            "Oval", 30000000, 56.67
        )
        toolSetDao.insertToolSet(toolSet)
        val byPONumber = toolSetDao.getToolSetByPO("00000")
        Assert.assertThat(byPONumber.PONumber, equalTo(toolSet.PONumber))
    }*/

}