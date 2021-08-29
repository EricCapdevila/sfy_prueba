package com.example.international_business_men.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.international_business_men.repository.Repository

class Factory(
    private val repository : Repository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TransactionsViewModel(repository) as T
    }
}