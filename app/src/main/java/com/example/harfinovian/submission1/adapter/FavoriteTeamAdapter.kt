package com.example.harfinovian.submission1.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.item_team_list
import com.example.harfinovian.submission1.entity.db.FavoriteTeam
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team_list.*

class FavoriteTeamAdapter(private val items: List<FavoriteTeam>,
                          val listener: (FavoriteTeam) -> Unit) : RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(item_team_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(items: FavoriteTeam, listener: (FavoriteTeam) -> Unit) = with(itemView) {
            team_name_txt.text = items.teamName


            itemView.setOnClickListener { listener(items) }
        }

    }

}