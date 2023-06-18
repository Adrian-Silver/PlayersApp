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
import com.example.room_playersapp.databinding.FragmentPlayerDetailsBinding


class PlayerDetailsFragment : Fragment() {

    private val navigationArgs: PlayerDetailsFragmentArgs by navArgs()
    lateinit var player: Player

    private val viewModel: PlayerViewModel by activityViewModels {
        PlayerViewModelFactory(
            (activity?.application as PlayerApplication).database.playerDao()
        )
    }

    private var _binding: FragmentPlayerDetailsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /* Initialize views,
        functions to manipulate buttons -> edit, delete
        back button
        viewbinding - refer to first task

        // functions to manipulate db

         */



        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_player_details, container, false)

        _binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.playerId

        viewModel.fetchPlayer(id).observe(this.viewLifecycleOwner) { selectedPlayer ->
            player = selectedPlayer
            bind(player)

        }

//        binding.btnPlayerEdit.setOnClickListener {
//            val action = PlayerDetailsFragmentDirections.actionPlayerDetailsFragmentToAddPlayerFragment(
//                getString(R.string.add_player_title))
//            this.findNavController().navigate(action)
//        }
//
//        binding.btnPlayerDelete.setOnClickListener {
//            deletePlayer()
//        }



    }

    private fun bind(player: Player) {

        binding.apply {
            nameDetailEtv.text = player.playerName
            ageDetailEtv.text = player.playerAge.toString()
            valueDetailEtv.text = player.playerValue.toString()
            positionDetailEtv.text = player.playerPosition

            btnPlayerEdit.setOnClickListener { editPlayer() }
            btnPlayerDelete.setOnClickListener { deletePlayer() }
        }

    }

    private fun deletePlayer() {
        // Delete player details
        viewModel.deletePlayer(player)

        // Navigate back to list of players fragment
        findNavController().navigateUp()

    }

    private fun editPlayer() {

        val action = PlayerDetailsFragmentDirections.actionPlayerDetailsFragmentToAddPlayerFragment(
            "Edit Player Details",
            player.id
        )
        this.findNavController().navigate(action)

    }



}