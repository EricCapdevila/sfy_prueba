package com.example.sfy_prueba.views.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sfy_prueba.databinding.ImageListFragmentBinding
import com.example.sfy_prueba.repository.models.Photo
import com.example.sfy_prueba.utils.Constants.PHOTO_FRAGMENT
import com.example.sfy_prueba.views.activity.MainActivity
import com.example.sfy_prueba.views.adapter.BasicAdapter

class ImageListFragment : PhotosBaseFragment() {

    private lateinit var binding: ImageListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImageListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    var onImageSelected  : (Int) -> View.OnClickListener = { i ->
        View.OnClickListener {
            (activity as MainActivity).goToFragment(ImageFragment.newInstance(i), PHOTO_FRAGMENT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageListFragmentRecycler.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = viewModel.photos.value?.let { BasicAdapter(onImageSelected, it) }
        }
    }

}