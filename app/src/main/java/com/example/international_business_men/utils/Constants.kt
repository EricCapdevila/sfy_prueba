package com.example.international_business_men.utils

import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction
import java.math.BigDecimal

object Constants {

    //api constants
    val BASE_URL : String = "http://quiet-stone-2094.herokuapp.com"
    val SERVICE_LOGS : String = "SERVICE_LOGS"

    // currency constants
    val EUR = "EUR"
    val AUD = "AUD"
    val CAD = "CAD"
    val USD = "USD"

    // fragment tags
    val PRODUCTS_FRAGMENT = "PRODUCTS_FRAGMENT"
    val PRODUCT_TRANSACTIONS_FRAGMENT = "PRODUCT_TRANSACTIONS_FRAGMENT"
    val ERROR_FRAGMENT = "ERROR_FRAGMENT"
    // fragments params
    val PRODUCT_ID = "PRODUCT_ID"


}