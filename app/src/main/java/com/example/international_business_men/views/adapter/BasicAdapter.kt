package com.example.international_business_men.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.international_business_men.R
import com.example.international_business_men.databinding.RecyclerStandardItemBinding

class BasicAdapter(val onClick : ((id : String) -> Unit)?, val data : List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BasicViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_standard_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BasicViewHolder).setUpItem(data[position], onClick)
    }

}

class BasicViewHolder( itemView: View)
    : RecyclerView.ViewHolder(itemView) {
    var binding = RecyclerStandardItemBinding.bind(itemView)

    fun setUpItem(item : String, onClick : ((id : String) -> Unit)?){
        binding.recyclerItemStandardItemText.run{
            text = item
            if(onClick == null){
                setTextAppearance(R.style.text_background)
            } else {
                setTextAppearance(R.style.text_button_background)
                setOnClickListener { View.OnClickListener { onClick(item) } }
            }
        }

    }
}

