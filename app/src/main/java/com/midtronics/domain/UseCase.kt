package com.midtronics.domain

interface UseCase<T> {
    suspend fun apply(query: String? = null): Result<T>
}