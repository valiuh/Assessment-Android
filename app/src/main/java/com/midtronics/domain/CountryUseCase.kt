package com.midtronics.domain

import com.midtronics.data.Country
import com.midtronics.data.api.CountryService

class CountryUseCase(
    private val service: CountryService
): UseCase<Country> {

    override suspend fun apply(query: String?): Result<Country> {
        return try {
            if (query != null){
                val response = service.getCountryByName(query)
                Result.success(response.first())
            } else {
                Result.failure(Throwable("Empty Data Exception"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}