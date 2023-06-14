package com.example.room_playersapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.room_playersapp.data.Player
import com.example.room_playersapp.databinding.PlayerListItemBinding


class PlayerAdapter(private val onItemClicked: (Player) -> Unit) :
//    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    ListAdapter<Player, PlayerAdapter.PlayerViewHolder>(DiffCallback) {

//
//    class PlayerAdapterB(private val onItemClicked: (Player) -> Unit) : ListAdapter<Player, PlayerViewHolder>(
//        DiffCallback) {
//
//    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
//
//        val binding = PlayerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//        // create a cardview for each item click
//        binding.playerCv.setOnClickListener {
//            val position = binding.adapterPosition
//            onItemClicked(getItem(position))
//
//        }

        return PlayerViewHolder(
            PlayerListItemBinding.inflate(LayoutInflater.from(parent.context))
        )

//        return PlayerViewHolder(PlayerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//        return PlayerViewHolder(PlayerListItemBinding.inflate(LayoutInflater.from(parent.context)))


    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
//        holder.bind(onItemClicked[position])

        val currentItem = getItem(position)

        // itemView inatoka wapi ???
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }
        holder.bind(currentItem)

    }
//
//    override fun getItemCount(): Int {
//
//        return onItemClicked.size
//    }

    class PlayerViewHolder(private val binding: PlayerListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.nameEt.text = player.playerName
            binding.ageEt.text = player.playerAge.toString()
            binding.positionEt.text = player.playerPosition
            binding.valueEt.text = player.playerValue.toString()
        }
    }

    companion object {
//        private val diffUtil: DiffUtil<DiffUtil.ItemCallback<Item>()>)
        // defines how to compute if two words are the same or if the contents are same
        private val DiffCallback =
            object : DiffUtil.ItemCallback<Player>() {
                override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                    return oldItem.playerName == newItem.playerName
                }

            }
    }



//        val name : TextView =



}