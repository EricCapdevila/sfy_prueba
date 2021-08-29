package com.example.international_business_men.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.international_business_men.databinding.ActivityMainBinding
import com.example.international_business_men.view_model.TransactionsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpToolbar()
    }

    private fun setUpViewModel(){
        viewModel = ViewModelProvider(this).get(TransactionsViewModel::class.java)

    }

    private fun setUpToolbar(){
        setSupportActionBar(binding.mainActivityToolbar)
        // enable navigateUp ?

    }

    private fun setUpObservers(){
        // observe viewModel.dataHandler and viewModel.errorHandler
    }

    fun showToolbar(title : String){
        binding.mainActivityToolbar.run{
            this.title = title
            visibility = View.VISIBLE
        }
    }

    fun hideToolbar(){
        binding.mainActivityToolbar.visibility = View.GONE
    }

}