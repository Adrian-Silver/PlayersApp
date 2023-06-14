package com.example.room_playersapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.room_playersapp.data.Player
import com.example.room_playersapp.data.PlayerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PlayerViewModel(private val playerDao: PlayerDao): ViewModel() {

    // public livedata member variable to cache the list of players
    val allPlayers: LiveData<List<Player>> = playerDao.getAllPlayers().asLiveData()   // initialized the allPlayers flow from playerDao. Flow then converted to LiveData by calling asLiveData()

    // takes a player object and adds the data to the database in a non-blocking way
    private fun insertPlayerRecord(player: Player) {

        viewModelScope.launch {
            playerDao.insertPlayer(player)
        }
    }

    private fun getNewPlayerEntry(playerName: String, playerAge: String, playerPosition: String, playerValue: String): Player {

        return Player(
            playerName = playerName,
            playerAge = playerAge.toInt(),
            playerPosition = playerPosition,
            playerValue = playerValue.toDouble()
        )
    }

    fun addNewPlayer(playerName: String, playerAge: String, playerPosition: String, playerValue: String) {
        val newPlayer = getNewPlayerEntry(playerName, playerAge, playerPosition, playerValue)
        insertPlayerRecord(newPlayer)
    }

    fun isEntryValid(playerName: String, playerAge: String, playerPosition: String, playerValue: String) : Boolean {
        if (playerName.isBlank() || playerAge.isBlank() || playerPosition.isBlank() || playerValue.isBlank()) {
            return false
        }
        return true
    }

    // function to retrieve player
    fun fetchPlayer(id: Int): LiveData<Player> {
        return playerDao.getPlayer(id).asLiveData()

    }




    // function to delete player
    fun deletePlayer(player: Player) {
        viewModelScope.launch {
            playerDao.deletePlayer(player)
        }
    }

    // function to retrieve player entry (private)



    //function to update  existing player
    fun updatePlayer(
        playerId: Int,
        playerName: String,
        playerAge: String,
        playerPosition: String,
        playerValue: String
    ) {
        val updatedPlayer = getUpdatedPlayerEntry(playerId, playerName, playerAge, playerPosition, playerValue)
        updatePlayer(updatedPlayer)

    }


    // private function to update player (private) - launches coroutine to update player in a non-blocking way
    private fun updatePlayer(player: Player) {
        viewModelScope.launch {
            playerDao.updatePlayer(player)
        }
    }

    /* function to retrieve updated player entry
    - returns an instance of the player entity class with player info updated by the user

     */
    private fun getUpdatedPlayerEntry(
        playerId: Int,
        playerName: String,
        playerAge: String,
        playerPosition: String,
        playerValue: String
    ): Player {
        return Player(
            id = playerId,
            playerName = playerName,
            playerAge = playerAge.toInt(),
            playerPosition = playerPosition,
            playerValue = playerValue.toDouble()
        )
    }


}

// a Factory class that instantiates objects/ InventoryViewModel instance that can respond to lifecycle events
class PlayerViewModelFactory(private val playerDao: PlayerDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(playerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}