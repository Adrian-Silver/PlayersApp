package com.example.room_playersapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val playerName: String,
    @ColumnInfo(name = "age")
    val playerAge: Int,
    @ColumnInfo(name = "position")
    val playerPosition: String,
    @ColumnInfo(name = "price")
    val playerValue: Double
) {
}