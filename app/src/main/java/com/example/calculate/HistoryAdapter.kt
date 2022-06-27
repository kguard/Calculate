package com.example.calculate

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(val items: SharedPreferences):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView=LayoutInflater.from(parent.context).inflate(R.layout.itemholder,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history=items.getString("${position+1}","가나다")
        if (history != null) {
            holder.setHistory(history)
        }
    }

    override fun getItemCount(): Int {
        val t=items.all
        return t.size

    }
    class ViewHolder(v: View):RecyclerView.ViewHolder(v) {
        private var history=v.findViewById<TextView>(R.id.History)
        fun setHistory(a:String)
        {
            history.text = a
        }
    }
}
