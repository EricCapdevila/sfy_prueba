package com.example.sfy_prueba.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sfy_prueba.view_model.ImagesViewModel

open class PhotosBaseFragment : Fragment() {
    protected lateinit var viewModel: ImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ImagesViewModel::class.java)
    }

}