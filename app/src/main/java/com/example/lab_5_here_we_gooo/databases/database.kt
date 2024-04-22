package com.example.lab_5_here_we_gooo.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab_5_here_we_gooo.entities.CustomConvo
import com.example.lab_5_here_we_gooo.entities.User
import com.example.lab_5_here_we_gooo.objects.DerekDao
import com.example.lab_5_here_we_gooo.objects.UserDao

@Database(version = 1, entities = [CustomConvo::class])
abstract class CustomDerekBase: RoomDatabase(){
    abstract fun customDaoek(): DerekDao
}

@Database(version = 1, entities = [User::class])
abstract class CustomUserDatabase: RoomDatabase(){
    abstract fun customUserDao(): UserDao
}

//class Converters {
//    private val _sep = " && "
//
//    @TypeConverter
//    fun stringListToString(entries: List<String>): String{
//        return entries.joinToString(_sep)
//    }
//
//    @TypeConverter
//    fun stringToListString(data: String):List<String>{
//        if(data.contains(_sep)){
//            return data.replace("[", "").replace("]", "").split(_sep)
//        }
//        return emptyList()
//    }
//
//}


//@Database(version = 1, e)
