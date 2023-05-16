package com.example.recyclerview

import android.content.Context
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class AudioAdapter(
    private val audioFiles: List<audioFileModel>) :
    RecyclerView.Adapter<AudioAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val audioFile = audioFiles[position]
        holder.nameTextView.text = audioFile.name

        holder.itemView.setOnClickListener {

            
        }
    }

    override fun getItemCount(): Int {
        return audioFiles.size
    }
}

