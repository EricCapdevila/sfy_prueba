package com.example.international_business_men.repository.models

class ProductTransactions (
    var sku : String,
    var convertedTransactions : List<ConvertedTransaction>,
    var total : String
)

class ConvertedTransaction(
    var quantity : String
)