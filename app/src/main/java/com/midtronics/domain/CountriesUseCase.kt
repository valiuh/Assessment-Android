package com.midtronics.domain

import android.content.Context
import com.midtronics.R

class CountriesUseCase(
    private val context: Context
): UseCase<List<String>> {

    override suspend fun apply(query: String?): Result<List<String>> {
        return try {
            val result =
                context
                    .resources
                    .getStringArray(R.array.countries_array)
                    .toList()

            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}