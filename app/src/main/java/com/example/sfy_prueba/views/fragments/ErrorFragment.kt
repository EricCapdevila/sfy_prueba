package com.example.sfy_prueba.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sfy_prueba.databinding.ErrorFragmentBinding

class ErrorFragment : PhotosBaseFragment() {

    private lateinit var binding: ErrorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ErrorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpMessage()
    }

    private fun setUpMessage() {
        binding.errorFragmentText.text = viewModel.errorHandler.value?.getCompleteMessage()
    }
}