package com.example.international_business_men.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.international_business_men.databinding.ProductTransactionsFragmentBinding
import com.example.international_business_men.utils.Constants.EUR
import com.example.international_business_men.utils.Constants.PRODUCT_ID
import com.example.international_business_men.view_model.TransactionsViewModel
import com.example.international_business_men.views.activity.ActivityManager
import com.example.international_business_men.views.adapter.BasicAdapter

open class TransactionsBaseFragment : Fragment() {
    protected lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TransactionsViewModel::class.java)
    }

}