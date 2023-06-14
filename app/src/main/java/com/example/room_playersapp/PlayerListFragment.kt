package com.example.room_playersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room_playersapp.databinding.FragmentPlayerListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PlayerListFragment : Fragment() {

    // To get a reference to the viewModel
    private val viewModel: PlayerViewModel by activityViewModels {
        PlayerViewModelFactory(
            (activity?.application as PlayerApplication).database.playerDao()
        )
    }

    private var _binding: FragmentPlayerListBinding? = null

    private val binding get() = _binding!!

//    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_player_list, container, false)
        _binding = FragmentPlayerListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // to navigate from PlayerListFragment to PlayerDetailsFragment -> use the adapter
        val adapter = PlayerAdapter {
            val action = PlayerListFragmentDirections.actionPlayerListFragmentToPlayerDetailsFragment(it.id)
            this.findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)


        viewModel.allPlayers.observe(this.viewLifecycleOwner) {players ->
            players.let {
                adapter.submitList(it)
            }

        }


//        // To update list view
//        GlobalScope.launch(Dispatchers.IO) {
//            adapter.submitList(viewModel.addNewPlayer())
//        }


        binding.floatingActionButton.setOnClickListener {
            val action = PlayerListFragmentDirections.actionPlayerListFragmentToAddPlayerFragment(
                getString(R.string.add_player_title)
            )
            this.findNavController().navigate(action)
        }

    }




}