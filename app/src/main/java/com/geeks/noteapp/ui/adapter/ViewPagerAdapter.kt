package com.geeks.noteapp.ui.adapter

import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.geeks.noteapp.R

class ViewPagerAdapter(private val titleList: List<String>, private val details: List<String>, private val images: List<Int>) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.txt_title)
        val itemDetails: TextView = itemView.findViewById(R.id.txt_title)
        val itemImage: ImageView = itemView.findViewById(R.id.txt_title)

        init {
            itemImage.setOnClickListener {v: View ->
                val position : Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item #${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_on_board_paging, parent, false))
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {

        holder.itemTitle.text = titleList[position]
        holder.itemDetails.text = details[position]
        holder.itemImage.setImageResource(images[position])

    }

    override fun getItemCount(): Int {
        return titleList.size
    }
}