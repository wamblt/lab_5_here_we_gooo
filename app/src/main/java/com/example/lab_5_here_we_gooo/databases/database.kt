package com.example.lab_5_here_we_gooo.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab_5_here_we_gooo.entities.CustomDerek
import com.example.lab_5_here_we_gooo.objects.DerekDao

@Database(version = 1, entities = [CustomDerek::class])
abstract class CustomDerekBase: RoomDatabase(){
    abstract fun customDaoek(): DerekDao
}

