package com.midtronics.domain

import android.content.Context
import com.midtronics.R
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CountriesUseCaseTest {

    private lateinit var context: Context
    private lateinit var countriesUseCase: CountriesUseCase

    @Before
    fun setUp() {
        val context = Mockito.mock(Context::class.java)
        countriesUseCase = CountriesUseCase(context)
    }

    @Test
    fun `apply returns list of countries`() = runBlocking {
        val countryArray = arrayOf("Greece", "Greenland", "Grenada")
        Mockito.`when`(context.resources.getStringArray(R.array.countries_array)).thenReturn(countryArray)

        val result = countriesUseCase.apply()

        val expectedResult = Result.success(countryArray.toList())
        assertEquals(expectedResult, result)
    }

    @Test
    fun `apply returns failure result when exception occurs`() = runBlocking {
        val exception = Exception("Resource not found")
        Mockito.`when`(context.resources.getStringArray(R.array.countries_array)).thenThrow(exception)

        val result = countriesUseCase.apply(null)

        val expectedResult = Result.failure<List<String>>(exception)
        assertEquals(expectedResult, result)
    }
}