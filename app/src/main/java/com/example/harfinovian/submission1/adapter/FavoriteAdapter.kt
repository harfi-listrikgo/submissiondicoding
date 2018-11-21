package com.example.harfinovian.submission1.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.kotlinacademy.db.Favorite
import com.example.harfinovian.submission1.R.layout.item_list
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class FavoriteAdapter(val context: Context, private val items: List<Favorite>,
                   val listener: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(item_list, parent, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View, listener: (Favorite) -> Unit) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(items: Favorite, listener: (Favorite) -> Unit) = with(itemView) {
            date_txt.text = items.date
            home_txt.text = items.homeTeam
            away_txt.text = items.awayTeam
            score_txt.text = items.scoreHome?.let {
                items.scoreHome
            } + " VS " + items.scoreAway?.let {
                items.scoreAway
            }
            itemView.setOnClickListener { listener(items) }
        }

    }

}