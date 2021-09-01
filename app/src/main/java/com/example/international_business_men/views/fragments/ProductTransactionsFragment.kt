package com.example.international_business_men.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.international_business_men.databinding.ProductTransactionsFragmentBinding
import com.example.international_business_men.utils.Constants.EUR
import com.example.international_business_men.utils.Constants.PRODUCT_ID
import com.example.international_business_men.view_model.TransactionsViewModel
import com.example.international_business_men.views.activity.ActivityManager
import com.example.international_business_men.views.adapter.BasicAdapter

class ProductTransactionsFragment : TransactionsBaseFragment() {

    private lateinit var binding: ProductTransactionsFragmentBinding

    companion object {
        fun getInstance(id: String): ProductTransactionsFragment {
            return ProductTransactionsFragment().apply {
                arguments = Bundle().apply {
                    putString(PRODUCT_ID, id)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TransactionsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductTransactionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecycler()
        setUpSum()
    }

    private fun setUpRecycler() {
        binding.productTransactionsFragmentRecycler.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = viewModel.dataHandler.value
                ?.getAmountListByProduct(getProduct(), true, null)
                ?.let { BasicAdapter(null, it) }
        }
    }

    private fun setUpSum() {
        binding.productTransactionsFragmentSum.text = viewModel.dataHandler.value?.let {
            it.getSum(it.getAmountListByProduct(getProduct(), false, EUR), EUR)
        }
    }

    override fun onResume() {
        super.onResume()
        enableBackNavigation(true)
    }

    override fun onStop() {
        super.onStop()
        enableBackNavigation(false)
    }

    private fun enableBackNavigation(on: Boolean) {
        if (activity is ActivityManager) {
            if (on) (activity as ActivityManager).showToolbar(getProduct())
            else (activity as ActivityManager).hideToolbar()
        }
    }

    private fun getProduct(): String {
        arguments?.let { return it.getString(PRODUCT_ID, "") }
        return ""
    }
}