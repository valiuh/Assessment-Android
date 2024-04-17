package com.midtronics.domain

import com.midtronics.data.Profile
import com.midtronics.data.assets.ProfileReader

class ProfileUseCase(
    private val reader: ProfileReader
): UseCase<Profile> {

    override suspend fun apply(query: String?): Result<Profile> {
        return try {
            val response = reader.getProfile()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}