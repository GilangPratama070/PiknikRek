package com.gilangpratama.piknikrek.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gilangpratama.piknikrek.R
import com.gilangpratama.piknikrek.data.local.WisataEntity
import com.gilangpratama.piknikrek.databinding.ItemWisataBinding

class WisataAdapter(private val onClicked: OnItemClicked): ListAdapter<WisataEntity, WisataAdapter.WisataViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val binding = ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WisataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        val wisata = getItem(position)
        holder.bind(wisata)
    }

    inner class WisataViewHolder(private val binding: ItemWisataBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(wisataEntity: WisataEntity) {
            binding.apply {
                tvNamaWisata.text = wisataEntity.name
                tvLokasi.text = wisataEntity.address
                onClicked.onItemClicked(wisataEntity.id)

                val options = RequestOptions()
                    .transform(RoundedCorners(16))
                    .placeholder(R.color.purple_200)

                Glide.with(itemView.context)
                    .load(wisataEntity.imageAvatar)
                    .apply(options)
                    .into(ivWisata)
            }
        }
    }

    interface OnItemClicked {
        fun onItemClicked(id: Int?)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WisataEntity>() {
            override fun areItemsTheSame(oldItem: WisataEntity, newItem: WisataEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WisataEntity, newItem: WisataEntity): Boolean =
                oldItem.name == newItem.name
        }
    }
}