package com.example.lab_5_here_we_gooo.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "derekEntity")
data class CustomDerek(@PrimaryKey val id: String, @ColumnInfo(name = "derek name") val superDEREKname: String, val superDEREKage: Int)