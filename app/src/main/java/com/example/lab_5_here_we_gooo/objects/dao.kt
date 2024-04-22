package com.example.lab_5_here_we_gooo.objects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lab_5_here_we_gooo.entities.CustomConvo
import com.example.lab_5_here_we_gooo.entities.User

@Dao
interface DerekDao{
    @Insert
    fun insertCustomDerek(derek: CustomConvo)
    @Delete
    fun deleteDerek(derek: CustomConvo)
    @Update
    fun updateDerek(derek: CustomConvo)
    @Query("Select * from derekEntity")
    fun getAllDereks(): List<CustomConvo>
    @Query("Select * from derekEntity where id = :derekId")
    fun getDerekById(derekId: Long): CustomConvo

}

interface UserDao{
    @Insert
    fun insertUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Update
    fun updateUser(user: User)
    @Query("Select * from userEntity")
    fun getAllUsers(): List<User>


}