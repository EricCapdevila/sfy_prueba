package com.example.international_business_men.view_model

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.international_business_men.repository.Repository
import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction

class TransactionsViewModel(val repository: Repository) : ViewModel(){

    private lateinit var ratesObserver : Observer<List<Rate>?>
    private lateinit var transactionsObserver : Observer<List<Transaction>?>


    init{
        repository.run{
            getRates()
            rates.observeForever(ratesObserver)
            getTransactions()
            transactions.observeForever(transactionsObserver)
        }
    }

    override fun onCleared() {
        super.onCleared()
        clearObservers()
    }

    private fun clearObservers(){
        repository.run{
            rates.removeObserver(ratesObserver)
            transactions.removeObserver(transactionsObserver)
        }
    }

}