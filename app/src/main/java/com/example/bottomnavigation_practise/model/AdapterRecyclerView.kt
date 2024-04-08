package com.example.bottomnavigation_practise.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigation_practise.R

class MyAdapter(private val itemList: List<String>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewWord)
        val soundIcon: ImageView = itemView.findViewById(R.id.imageViewPlay)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.imageViewFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = itemList[position]

        // Обработка нажатий на иконки
        holder.soundIcon.setOnClickListener {
            // Обработка нажатия на иконку звука
            // Здесь можно добавить код для воспроизведения звука или другой логики
        }

        holder.favoriteIcon.setOnClickListener {
            // Обработка нажатия на иконку избранного
            // Здесь можно добавить код для добавления/удаления избранного или другой логики
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}