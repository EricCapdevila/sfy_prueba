package com.example.international_business_men.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.international_business_men.R
import com.example.international_business_men.databinding.RecyclerRegularTextBinding
import com.example.international_business_men.databinding.RecyclerRegularTextButtonBinding

class BasicAdapter(private val onClick : ((id : String) -> Unit)?, private val data : List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (onClick == null) TextViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_regular_text, parent, false))
        else TextButtonViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_regular_text_button, parent, false), onClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TextViewHolder) holder.setUpItem(data[position])
        if (holder is TextButtonViewHolder) holder.setUpItem(data[position])
    }

}

class TextViewHolder(view: View )
    : RecyclerView.ViewHolder(view) {
    var binding =  RecyclerRegularTextBinding.bind(view)
    fun setUpItem(item : String){
      binding.recyclerTextItemText.text = item
    }
}

class TextButtonViewHolder(view: View, val onClick : (id : String) -> Unit)
    : RecyclerView.ViewHolder(view) {
    var binding = RecyclerRegularTextButtonBinding.bind(view)
    fun setUpItem(item : String){
        binding.recyclerTextButtonItemText.run{
            text = item
            setOnClickListener { onClick(item) }
        }
    }
}

