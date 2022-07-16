package com.devstone.punchesmanager

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devstone.punchesmanager.data.AppDatabase
import com.devstone.punchesmanager.data.dao.ToolSetDao
import com.devstone.punchesmanager.data.entities.ToolSet
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
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

    //Test to determine if the insert and retrieve functions are effective.
    @Test
    fun insertToolSetTest() = runBlocking {
        val toolSet = ToolSet(
            "00000", 2, 30,
            "Oval", 30000000, 56.67, true
        )
        toolSetDao.insertToolSet(toolSet)
        val byPONumber = toolSetDao.getToolSetByPO("00000")
        Assert.assertThat(
            byPONumber, equalTo(toolSet),
        )
    }

    //Test to determine if the delete functionality is effective.
    @Test
    fun deleteToolSetTest(): Unit = runBlocking {

        //insert toolset into the in memory database
        val toolSet = ToolSet(
            "00000", 2, 30,
            "Oval", 30000000, 56.67, true
        )
        toolSetDao.insertToolSet(toolSet)

        //Check to see if in the database
        val check = toolSetDao.getToolSetByPO("00000")
        Assert.assertThat(
            check, equalTo(toolSet),
        )

        //Deletes the tool set and checks in database for toolset.
        toolSetDao.deleteToolSet(check)
        Assert.assertThat(toolSetDao.getToolSetByPO("00000"), equalTo(null))

    }

}