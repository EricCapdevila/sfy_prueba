package com.example.international_business_men.repository.models

class Transactions(
    var data: List<Transaction>
)


class Transaction (
    var sku : String,
    var amount : String,
    var currency : String
)