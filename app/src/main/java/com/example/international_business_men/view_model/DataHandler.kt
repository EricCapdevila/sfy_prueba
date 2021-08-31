package com.example.international_business_men.view_model

import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList

class DataHandler(
    private val transactions: List<Transaction>,
    private val rates : List<Rate>) {

    fun getProductList() : List<String> {
        val list : ArrayList<String> = ArrayList()
        transactions.forEach {
            if(!list.contains(it.sku)) list.add(it.sku)
        }
        return list
    }

    fun getAmountListByProduct(sku : String, formatted : Boolean, convertTo : String?) : MutableList<String>{
        val list = mutableListOf<String>()
        getTransactionsByProduct(sku).forEach {
            var item = it.amount
            convertTo?.let{ to -> if (to.isNotEmpty()) item = getAmountConverted(it.amount, it.currency, to)}
            if(formatted) item = it.amount.prettifyAmount(it.currency)
            list.add(item)
        }
        return list;
    }

    fun getSum(numList : List<String>, currency: String) : String{
        var result = BigDecimal(0)
        numList.forEach {
            result = result.add(BigDecimal(it))
        }
        return result.setScale(2,RoundingMode.HALF_EVEN).toString().prettifyAmount(currency)
    }

    private fun getTransactionsByProduct(sku : String) : List<Transaction> {
        val list : ArrayList<Transaction> = ArrayList()
        transactions.forEach {
            if(sku == it.sku) list.add(it)
        }
        return list
    }


    private fun getAmountConverted(amount : String, from : String, to : String) : String {
        if(from == to) return amount
        var result = BigDecimal(amount)
        getRatesToApply(from, to).forEach {
            result = result.multiply(BigDecimal(it))
        }
        return result.setScale(2, RoundingMode.HALF_EVEN).toString()
    }

    private fun getRatesToApply(from : String, to : String) : MutableList<String> {
        val steps = getConversionSteps(from, to).asReversed()
        val ratesToApply = mutableListOf<String>()
        var fromRegister : String = from
        steps.forEach { step ->
            var foundARate = false
            step.forEach { rate ->
                if(!foundARate && rate.from == fromRegister){
                    ratesToApply.add(rate.rate)
                    fromRegister = rate.to
                    foundARate = true
                }
            }
        }
        return ratesToApply
    }


    /*
    return a list of lists of rates, they are all connected, one list has rates that only have the "to" we need
    and another lists contains the conversion of the "froms" in that first list, now used as "to"s. We repeat the
    process until we reach a rate that has the "from"we need.
     */
    private fun getConversionSteps(from : String, to : String) : List<List<Rate>>{
        val steps = mutableListOf<List<Rate>>()
        val listOfFroms = mutableListOf<String>()
        steps.add(getStep(listOf(to), listOfFroms))
        while(!listOfFroms.contains(from)){
            steps.add(getStep(mutableListOf<String>().apply { addAll(listOfFroms) }, listOfFroms))
        }
        return steps
    }

    /*
    get all the rates that have the "to"s and then store their "froms" for the next step, to use them as "to"s
     */
    private fun getStep(to : List<String>, fromsToUpdate : MutableList<String>) : List<Rate>{
        val step  = ArrayList<Rate>()
        fromsToUpdate.clear()
        rates.forEach { rate ->
            to.forEach {
                if(rate.to == it) {
                    step.add(rate)
                    fromsToUpdate.add(rate.from)
                }
            }
        }
        return step
    }

    private fun String.prettifyAmount(currency: String) : String {
        val symbols = DecimalFormatSymbols().apply {
            decimalSeparator = ','
            groupingSeparator = '.'
        }
        return DecimalFormat("###,###.00", symbols).format(BigDecimal(this).toDouble())+
                " " + Currency.getInstance(currency).symbol
    }
}