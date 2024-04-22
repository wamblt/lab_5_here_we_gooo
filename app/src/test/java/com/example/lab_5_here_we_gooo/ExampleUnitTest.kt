package com.example.lab_5_here_we_gooo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.lab_5_here_we_gooo.databases.CustomConvoBase
import com.example.lab_5_here_we_gooo.databases.CustomUserDatabase
import com.example.lab_5_here_we_gooo.entities.CustomConvo
import com.example.lab_5_here_we_gooo.entities.User
import com.example.lab_5_here_we_gooo.objects.ConvoDao
import com.example.lab_5_here_we_gooo.objects.UserDao
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest() {
    private lateinit var convoDao: ConvoDao
    private lateinit var cDb: CustomConvoBase

    private lateinit var userDao: UserDao
    private lateinit var uDb: CustomUserDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        cDb = Room.inMemoryDatabaseBuilder(context, CustomConvoBase::class.java).build()
        uDb = Room.inMemoryDatabaseBuilder(context, CustomUserDatabase::class.java).build()

        convoDao = cDb.customDaoek()
        userDao = uDb.customUserDao()
    }
    @After
    @Throws(IOException::class)
    fun closeDb(){
        cDb.close()
        uDb.close()
    }

    //this is for the convo database

    @Test
    @Throws(Exception::class)
    fun createCustomConvoCheck(){
        val convo = CustomConvo(Random(System.currentTimeMillis()).nextLong(), "test")

        convoDao.insertCustomDerek(convo)
        assert(convoDao.getAllDereks().size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun getConvoByIdCheck(){
        val id = Random(System.currentTimeMillis()).nextLong()

        val convo = CustomConvo(id, "test")
        convoDao.insertCustomDerek(convo)

        val getConvo = convoDao.getDerekById(id)
        assertTrue(convo.equals(getConvo))
    }

    //and for user database

    @Test
    @Throws(Exception::class)
    fun createCustomUserCheck(){
        val user = User(Random(System.currentTimeMillis()).nextLong(), "testname", 25)

        userDao.insertUser(user)

        assert(userDao.getAllUsers().size == 1)

    }


}