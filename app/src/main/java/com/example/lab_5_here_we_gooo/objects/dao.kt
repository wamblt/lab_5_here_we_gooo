package com.example.lab_5_here_we_gooo.objects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lab_5_here_we_gooo.entities.CustomDerek

@Dao
interface DerekDao{
    @Insert
    fun insertCustomDerek(derek: CustomDerek)
    @Delete
    fun deleteDerek(derek: CustomDerek)
    @Update
    fun updateDerek(derek: CustomDerek)
    @Query("Select * from derekEntity")
    fun getAllDereks(): List<CustomDerek>

}