package com.example.whatisthisapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PlayerRecordsAdapter(context: Context, private val playerRecords: List<PlayerRecord>) :
    ArrayAdapter<PlayerRecord>(context, 0, playerRecords) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        val viewHolder: ViewHolder

        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.player_records_items, parent, false)
            viewHolder = ViewHolder(listItemView)
            listItemView.tag = viewHolder
        } else {
            viewHolder = listItemView.tag as ViewHolder
        }

        val playerRecord = playerRecords[position]
        viewHolder.playerNameTextView.text = playerRecord.name // Set the modified name
        viewHolder.quizScoreTextView.text = playerRecord.quizScore.toString()

        return listItemView!!
    }

    class ViewHolder(itemView: View) {
        val playerNameTextView: TextView = itemView.findViewById(R.id.playerNameTextView)
        val quizScoreTextView: TextView = itemView.findViewById(R.id.quizScoreTextView)
    }
}