package com.midtronics.domain

import com.midtronics.data.Country
import com.midtronics.data.CountryName
import com.midtronics.data.api.CountryService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CountryUseCaseTest {

    private lateinit var service: CountryService
    private lateinit var countryUseCase: CountryUseCase

    @Before
    fun setUp() {
        val service = Mockito.mock(CountryService::class.java)
        countryUseCase = CountryUseCase(service)
    }

    @Test
    fun `apply returns country when query is not null`() = runBlocking {
        val query = "Argentina"
        val response = listOf(Country(
            capital = listOf("Buenos Aires"),
            population = "43131966",
            area = "2780400",
            region = "Americas",
            subregion = "South America",
            name = CountryName(
                common = "Argentina",
                official = "Argentina"
            )
        ))

        Mockito.`when`(service.getCountryByName(query)).thenReturn(response)

        val result = countryUseCase.apply(query)

        val expectedResult = Result.success(response.first())
        assertEquals(expectedResult, result)
    }

    @Test
    fun `apply returns failure result when query is null`() = runBlocking {
        val result = countryUseCase.apply()
        val expectedResult = Result.failure<Country>(Throwable("Empty Data Exception"))
        assertEquals(expectedResult, result)
    }

    @Test
    fun `apply returns failure result when service throws exception`() = runBlocking {
        val query = "CountryName"
        Mockito.`when`(service.getCountryByName(query)).thenThrow(Exception("Service exception"))

        val result = countryUseCase.apply(query)

        assertEquals(Result.failure<Country>(Exception("Service exception")), result)
    }
}