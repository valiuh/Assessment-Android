package com.midtronics.domain

import com.midtronics.data.Education
import com.midtronics.data.Experience
import com.midtronics.data.Profile
import com.midtronics.data.assets.ProfileReader
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class ProfileUseCaseTest {

    @Test
    fun `apply returns success result when reader returns profile`() = runBlocking {

        val profile = Profile(
            firstName = "Anton",
            lastName = "Valiukh",
            experience = listOf(Experience(
                companyName = "Van Lanschot Kempen",
                position = "Expert Android developer",
                startDate = "22.08.2022",
                endDate = null
            )),
            education = listOf(Education(
                placeName = "Dnipropetrovsk National University",
                education = "Master Degree in Computer Science",
                startDate = "01.09.2008",
                endDate = "01.06.2009"
            )),
            avatar = null
        )

        val reader = Mockito.mock(ProfileReader::class.java)
        Mockito.`when`(reader.getProfile()).thenReturn(profile)

        val useCase = ProfileUseCase(reader)

        val result = useCase.apply()

        assertEquals(Result.success(profile), result)
    }

    @Test
    fun `apply returns failure result when reader throws exception`() = runBlocking {
        val reader = Mockito.mock(ProfileReader::class.java)
        val exception = Exception("Reader failed")
        Mockito.`when`(reader.getProfile()).thenThrow(exception)

        val useCase = ProfileUseCase(reader)

        val result = useCase.apply()

        assertEquals(Result.failure<Profile>(exception), result)
    }
}