package com.example.international_business_men.views.fragments

import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.international_business_men.databinding.ProductsFragmentBinding
import com.example.international_business_men.utils.Constants.PRODUCT_TRANSACTIONS_FRAGMENT
import com.example.international_business_men.view_model.TransactionsViewModel
import com.example.international_business_men.views.activity.ActivityManager
import com.example.international_business_men.views.adapter.BasicAdapter

class ProductsFragment : Fragment() {

    private lateinit var viewModel : TransactionsViewModel
    private lateinit var binding : ProductsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TransactionsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.productsFragmentRecycler.run{
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = viewModel.dataHandler.value?.getProductList()?.let { BasicAdapter(onProductSelected, it) }
        }
    }

    private val onProductSelected : (id : String) -> Unit =  {
        if(activity is ActivityManager) {
            (activity as ActivityManager).goToFragment(ProductTransactionsFragment.getInstance(it), PRODUCT_TRANSACTIONS_FRAGMENT)
        }
    }
}