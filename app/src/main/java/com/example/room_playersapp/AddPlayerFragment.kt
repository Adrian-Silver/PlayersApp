package com.example.room_playersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_playersapp.data.Player
import com.example.room_playersapp.databinding.FragmentAddPlayerBinding


class AddPlayerFragment : Fragment() {

    private val viewModel: PlayerViewModel by activityViewModels {
        PlayerViewModelFactory(
            (activity?.application as PlayerApplication).database.playerDao()
        )
    }

    private val navigationArgs: PlayerDetailsFragmentArgs by navArgs()

    lateinit var player: Player


    private var _binding: FragmentAddPlayerBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /* initialize views
            set button functionality
            Create a cancel button
            back button functionality

            viewModel functionality **

         */

        // Inflate the layout for this fragment
        _binding = FragmentAddPlayerBinding.inflate(inflater, container, false)
        return binding.root

//        return inflater.inflate(R.layout.fragment_add_player, container, false)
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
                binding.playerNameEt.text.toString(),
                binding.playerAgeEt.text.toString(),
                binding.playerPositionEt.text.toString(),
                binding.playerValueEt.text.toString()
        )
    }

    // bind views with player info passed in
    private fun bind(player: Player) {
        val value = "%.2f".format(player.playerValue)
        val age = "%d".format(player.playerAge)

        binding.apply {

            playerNameEt.setText(player.playerName)
            playerAgeEt.setText(age)
            playerPositionEt.setText(player.playerPosition)
            playerValueEt.setText(value)

            btnSavePlayer.setOnClickListener {
                updatePlayer()
            }
        }
    }


    private fun addNewPlayer() {
        if (isEntryValid()) {
            viewModel.addNewPlayer(
                binding.playerNameEt.text.toString(),
                binding.playerAgeEt.text.toString(),
                binding.playerPositionEt.text.toString(),
                binding.playerValueEt.text.toString()
            )
            // after adding a new item, when button is clicked, will navigate to PlayerListFragment
            val action = AddPlayerFragmentDirections.actionAddPlayerFragmentToPlayerListFragment()
            findNavController().navigate(action)
        }
    }

    // Update Player Details
    private fun updatePlayer() {

        if (isEntryValid()) {
            viewModel.updatePlayer(
                this.navigationArgs.playerId,

                this.binding.playerNameEt.text.toString(),
                this.binding.playerAgeEt.text.toString(),
                this.binding.playerPositionEt.text.toString(),
                this.binding.playerValueEt.text.toString()
            )

            val action = AddPlayerFragmentDirections.actionAddPlayerFragmentToPlayerListFragment()
            findNavController().navigate(action)
        }

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Functionality to retrieve and edit player details
        val id = navigationArgs.playerId

        if (id > 0) {
            viewModel.fetchPlayer(id).observe(this.viewLifecycleOwner) { selectedPlayer ->
                player = selectedPlayer
                bind(player)
            }
        } else {
                binding.btnSavePlayer.setOnClickListener {
                    addNewPlayer()
                }
            }
        }

}