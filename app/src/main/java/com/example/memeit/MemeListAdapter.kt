package com.example.memeit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memeit.databinding.MemeViewBinding
import com.example.memeit.network.Meme
import com.example.memeit.utils.CustomClickListener

class MemeListAdapter(private val onClickListener: CustomClickListener) :
    ListAdapter<Meme, MemeListAdapter.MemePhotoViewHolder>(DiffCallback) {

    class MemePhotoViewHolder(private var binding: MemeViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meme: Meme, onClickListener: CustomClickListener) {
            binding.memeImage = meme
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onClickListener.onMemeClickListen(meme)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemePhotoViewHolder {
        return MemePhotoViewHolder(MemeViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MemePhotoViewHolder, position: Int) {
        val memePhoto = getItem(position)
        holder.bind(memePhoto, onClickListener)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Meme>() {
        override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem.url == newItem.url
        }
    }
}