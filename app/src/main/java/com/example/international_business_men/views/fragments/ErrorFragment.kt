package com.example.international_business_men.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.international_business_men.databinding.ErrorFragmentBinding
import com.example.international_business_men.databinding.ProductsFragmentBinding
import com.example.international_business_men.view_model.TransactionsViewModel

class ErrorFragment : Fragment(){

    private lateinit var viewModel : TransactionsViewModel
    private lateinit var binding : ErrorFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionsViewModel::class.java)
    }

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

    private fun setUpMessage(){
        binding.errorFragmentText.text = viewModel.errorHandler.value?.getCompleteMessage()
    }
}