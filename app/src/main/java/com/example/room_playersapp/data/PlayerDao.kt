package com.example.room_playersapp.data

import android.content.ClipData.Item
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ignores a new item if its primary key is already in the database
    suspend fun insertPlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("DELETE FROM player")
    suspend fun deleteAllPlayers()

    @Query("SELECT * from player WHERE id = :id") // Retrieve a player's data based on their ID
    fun getPlayer(id: Int): Flow<Player>

    @Query("SELECT * from player ORDER BY name ASC") // Retrieve all players and order them in ascending order based on their name.
    fun getAllPlayers(): Flow<List<Player>>
}