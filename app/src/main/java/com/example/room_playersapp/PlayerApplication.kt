package com.example.room_playersapp

import android.app.Application
import com.example.room_playersapp.data.PlayerRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlayerApplication: Application() {

    /*
        No need to cancel this scope as it'll be torn down with the process
     */
//    val applicationScope = CoroutineScope(SupervisorJob())


    // use by lazy to only create db and repository when first needed
    val database: PlayerRoomDatabase by lazy { PlayerRoomDatabase.getDatabase(this) }

//    val database: PlayerRoomDatabase by lazy { PlayerRoomDatabase.getDatabase(this, applicationScope) }
//    val repository by lazy { PlayerRepository(database.playerDao()) }
}