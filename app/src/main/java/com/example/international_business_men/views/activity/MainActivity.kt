package com.example.international_business_men.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.international_business_men.databinding.ActivityMainBinding
import com.example.international_business_men.utils.Constants.ERROR_FRAGMENT
import com.example.international_business_men.utils.Constants.PRODUCTS_FRAGMENT
import com.example.international_business_men.utils.Constants.PRODUCT_TRANSACTIONS_FRAGMENT
import com.example.international_business_men.view_model.DataHandler
import com.example.international_business_men.view_model.ErrorHandler
import com.example.international_business_men.view_model.TransactionsViewModel
import com.example.international_business_men.views.fragments.ErrorFragment
import com.example.international_business_men.views.fragments.ProductTransactionsFragment
import com.example.international_business_men.views.fragments.ProductsFragment

class MainActivity : AppCompatActivity(), ActivityManager {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpToolbar()
        setUpObservers()
    }

    private fun setUpViewModel(){
        viewModel = ViewModelProvider(this).get(TransactionsViewModel::class.java)
    }

    private fun setUpToolbar(){
        setSupportActionBar(binding.mainActivityToolbar)
        //todo binding.mainActivityToolbar.navig sino en layout poner app:navigationIcon="?attr/homeAsUpIndicator"
        // o sino supportActionBar.setDisplayHomeAsUpEnabled(true)
        binding.mainActivityToolbar.setNavigationOnClickListener {
            View.OnClickListener {
                supportFragmentManager.popBackStack()
            }
        }
    }

    private fun setUpObservers(){
        viewModel.dataHandler.observe(this,
            Observer<DataHandler> { goToFragment(ProductsFragment(), PRODUCTS_FRAGMENT) })
        viewModel.errorHandler.observe(this,
        Observer<ErrorHandler> { goToFragment(ErrorFragment(), ERROR_FRAGMENT) })
    }


    override fun goToFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            transaction.replace(
                binding.activityMainFragmentContainer.id,
                fragment, tag)
        }  else {
            transaction.add(
                binding.activityMainFragmentContainer.id,
                fragment, tag)
        }
        //todo if (supportFragmentManager.fragments.isEmpty()) if first should not be added to back
        if(tag == PRODUCTS_FRAGMENT) transaction.addToBackStack(tag)
        transaction.commit()
    }

    override fun showToolbar(title : String){
        binding.mainActivityToolbar.run{
            this.title = title
            visibility = View.VISIBLE
        }
    }

    override fun hideToolbar(){
        binding.mainActivityToolbar.visibility = View.GONE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }

}