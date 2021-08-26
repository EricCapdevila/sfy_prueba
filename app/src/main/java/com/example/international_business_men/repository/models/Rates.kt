package com.example.international_business_men.repository.models

class Rates(
    var data : List<Rate>
)

class Rate (
    var from : String,
    var to : String,
    var rate : String
)

