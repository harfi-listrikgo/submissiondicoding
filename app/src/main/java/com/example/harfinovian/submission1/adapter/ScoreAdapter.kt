package com.example.harfinovian.submission1.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harfinovian.submission1.R.layout.item_list
import com.example.harfinovian.submission1.model.Event
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

class ScoreAdapter(private val items: List<Event>,
                   private val listener: (Event) -> Unit) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(item_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(items: Event, listener: (Event) -> Unit) = with(itemView) {
            date_txt.text = items.dateEvent
            home_txt.text = items.strHomeTeam
            away_txt.text = items.strAwayTeam
            score_txt.text = items.intHomeScore?.let {
                items.intHomeScore
            } + " VS " + items.intAwayScore?.let {
                items.intAwayScore
            }
            itemView.setOnClickListener { listener(items) }
        }

    }

}