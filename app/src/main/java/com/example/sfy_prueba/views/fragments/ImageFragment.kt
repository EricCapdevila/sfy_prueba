package com.example.sfy_prueba.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.sfy_prueba.databinding.ImageFragmentBinding
import com.example.sfy_prueba.repository.models.Photo
import com.squareup.picasso.Picasso

class ImageFragment : PhotosBaseFragment() {

    private lateinit var binding: ImageFragmentBinding
    private lateinit var photo: Photo

    companion object {
        private const val PHOTO_ID = "photo"
        fun newInstance(i: Int) = ImageFragment().apply {
            arguments = bundleOf(
                PHOTO_ID to i
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPhoto()
    }

    private fun getPhoto(){
        viewModel.photos.value?.let{ list ->
            arguments?.let {
                photo =  list[it.getInt(PHOTO_ID)]
            }
        }
    }

    private fun setUpImageView(){
        Picasso.get().load(photo.urls.regular).into(binding.imageFragmentImage);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpImageView()
    }


}