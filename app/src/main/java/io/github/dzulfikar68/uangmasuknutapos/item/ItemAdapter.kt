package io.github.dzulfikar68.uangmasuknutapos.item

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.dzulfikar68.uangmasuknutapos.R
import io.github.dzulfikar68.uangmasuknutapos.model.Money
import java.util.*

class ItemAdapter(private val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    var list: ArrayList<Money>? = null

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ItemViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.view_item, viewGroup, false)
        return ItemViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(
        vh: ItemViewHolder,
        i: Int
    ) {
        vh.fromx.text = "From: " + list!![i].from
        vh.descx.text = "Desc: " + list!![i].description
        vh.nominax.text = "Nomina: " + list!![i].nominal
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ItemViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var fromx: TextView
        var descx: TextView
        var nominax: TextView

        init {
            fromx = itemView.findViewById(R.id.fromx)
            descx = itemView.findViewById(R.id.descx)
            nominax = itemView.findViewById(R.id.nominax)
        }
    }

}