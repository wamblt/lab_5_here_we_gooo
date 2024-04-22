package com.example.lab_5_here_we_gooo.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "derekEntity")
data class CustomConvo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "derek name")
    val superDEREKname: String,
    val superDEREKS: List<String>)


@Entity(tableName = "userEntity")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "user name")
    val userName: String,
    val userAge: Int)
