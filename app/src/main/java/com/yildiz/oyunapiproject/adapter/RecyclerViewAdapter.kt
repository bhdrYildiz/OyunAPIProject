package com.yildiz.oyunapiproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yildiz.oyunapiproject.R
import com.yildiz.oyunapiproject.model.OyunResponse
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    private var oyunlist: ArrayList<OyunResponse.OyunResponseItem> = arrayListOf()

    class RowHolder(view : View) : RecyclerView.ViewHolder(view){
            fun bind(oyunModel: OyunResponse.OyunResponseItem){
                itemView.text_name.text = oyunModel.machineType
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RowHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
    )

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(oyunlist[position])
    }

    override fun getItemCount(): Int {
        return oyunlist.size
    }
    fun setOyunData(listoyun: ArrayList<OyunResponse.OyunResponseItem>){
        oyunlist.addAll(listoyun)
        notifyDataSetChanged()
    }

}