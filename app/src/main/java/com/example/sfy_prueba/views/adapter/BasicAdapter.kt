package com.example.sfy_prueba.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sfy_prueba.R
import com.example.sfy_prueba.databinding.RecyclerRegularTextBinding
import com.example.sfy_prueba.repository.models.Photo
import com.squareup.picasso.Picasso
import java.net.URI
import java.net.URL


class BasicAdapter(private val onImageSelected: (Int) -> View.OnClickListener,
                   private val photos:  List<Photo> ) :
    RecyclerView.Adapter<PhotoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        return PhotoItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_regular_text, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        with(holder){
            setUpTextAndImage(photos[position].user.name, photos[position].urls.thumb)
            setUpListener(onImageSelected(position))
        }
    }

}

class PhotoItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var binding = RecyclerRegularTextBinding.bind(view)
    fun setUpTextAndImage(text: String, src: String){
        with(binding){
            recyclerTextItemText.text = text
            Picasso.get().load(src).into(recyclerTextItemImage);
        }
    }

    fun setUpListener(event: View.OnClickListener) {
        binding.root.setOnClickListener(event)
    }
}



