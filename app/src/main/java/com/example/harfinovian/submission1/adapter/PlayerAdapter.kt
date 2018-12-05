package com.example.harfinovian.submission1.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.harfinovian.submission1.R.layout.item_player
import com.example.harfinovian.submission1.R.layout.item_team_list
import com.example.harfinovian.submission1.model.Player
import com.example.harfinovian.submission1.model.Team
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_player.*
import kotlinx.android.synthetic.main.item_team_list.*

class PlayerAdapter(private val items: List<Player>,
                  private val listener: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(item_player, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(items: Player, listener: (Player) -> Unit) = with(itemView) {

            player_name_txt.text = items.strPlayer
            position_txt.text = items.strPosition
            Glide.with(context).load(items.strThumb).into(player_img)

            itemView.setOnClickListener { listener(items) }
        }

    }

}