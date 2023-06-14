package com.example.room_playersapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
//import com.example.room_playersapp.player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Re-usable database class structure
@Database(entities = [Player::class], version = 1, exportSchema = false)
public abstract class PlayerRoomDatabase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao


    /* Custom Implementation of the RoomDatabase.Callback()
    * It gets a CoroutineScope s construction parameter
    * onCreate method is overriden to pre-populate the database.
    */

//    private class PlayerDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    var playerDao = database.playerDao()
//
//                    // Delete all content here.
//                    playerDao.deleteAllPlayers()
//
//                    // Add sample words.
//                    var player = Player(91,"Hanssen", 43, "Second", 980000.0)
//                    playerDao.insertPlayer(player)
//                    player = Player(90, "Fena", 23, "Second", 234000.0 )
//                    playerDao.insertPlayer(player)
//
//                    // TODO: Add your own players!
//                    player = Player(92, "playerName", 22, "playerPosition", 100000.0)
//                    playerDao.insertPlayer(player)
//                }
//            }
//        }
//    }


//
//    // For the pre-populated db
//    companion object {
//        @Volatile
//        private var INSTANCE: PlayerRoomDatabase? = null
//
//        fun getDatabase(
//            context: Context,
//            // Getting a coroutine scope as a parameter
//            scope: CoroutineScope
//        ): PlayerRoomDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    PlayerRoomDatabase::class.java,
//                    "player_database"
//                )
//                    .addCallback(PlayerDatabaseCallback(scope))
//                    .build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
//    }


    companion object {
        // INSTANCE - Keeps a reference to the database, when one has been created.
        // this helps maintain a single instance of the database opened at a given time

        @Volatile //a volatile variable is never cached (all read & writes done to and from memory)
        private var INSTANCE: PlayerRoomDatabase? = null

        fun getDatabase(context: Context): PlayerRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerRoomDatabase::class.java,
                    "player_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

//                return instance
                instance
            }
        }

    }

}
