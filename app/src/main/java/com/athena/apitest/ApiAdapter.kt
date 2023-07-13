package com.athena.apitest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.apitest.databinding.RecyclerItemBinding
import org.w3c.dom.Text

class ApiAdapter(val context: Context, val items : MutableList<ApiItem>) : Adapter<ApiAdapter.VH>() {

    class VH(val view : View) : ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
       val view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val textView = holder.view.findViewById<TextView>(R.id.tv)
        textView.text = items[position].title
    }

    override fun getItemCount(): Int {
    return items.size
    }
}