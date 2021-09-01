package com.example.international_business_men

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.international_business_men.repository.models.Rate
import com.example.international_business_men.repository.models.Transaction
import com.example.international_business_men.view_model.DataHandler

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DataHandlerTest {

    private val transactions = listOf(
        Transaction("x", "10,5", "EUR"),
        Transaction("x", "10,5", "CAD"),
        Transaction("x", "10,5", "USD"),
        Transaction("x", "10,5", "AUD"))

//    AUD > CAD > EUR 1.23 -> 1.17
    private val transactionsAudCadEur = listOf(
        Transaction("x", "10.5", "AUD"),
        Transaction("x", "10.5", "CAD"),
        Transaction("x", "10.5", "EUR")
    )

    private val rates = listOf(
        Rate("CAD", "EUR", "1.17"),
        Rate("EUR", "CAD", "0.85"),
        Rate("CAD","AUD", "0.81"),
        Rate("AUD", "CAD", "1.23"),
        Rate("EUR", "USD", "1.04"),
        Rate("USD", "EUR", "0.96"))


    @Test
    fun conversionIsCorrect() {
        val expected = listOf("15.11", "12.28", "10.5") // GETTING 12.92, 12.28, 10.5] Solo multiplica aud a cad
        val result = DataHandler(transactionsAudCadEur, rates)
            .getAmountListByProduct("x", false, "EUR")
        assertEquals(expected, result)
    }

}