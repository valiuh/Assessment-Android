package com.midtronics.data.assets

import android.content.Context
import com.midtronics.data.Education
import com.midtronics.data.Experience
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.ByteArrayInputStream
import java.io.FileNotFoundException

class ProfileReaderTest {

    private lateinit var context: Context
    private lateinit var profileReader: ProfileReader

    @Before
    fun setUp() {
        val context = Mockito.mock(Context::class.java)
        profileReader = ProfileReader(context, "profile.json")
    }

    @Test
    fun `getProfile returns profile from JSON file`() = runBlocking {
        val expectedProfile = com.midtronics.data.Profile(
            firstName = "Anton",
            lastName = "Valiukh",
            experience = listOf(
                Experience(
                    companyName = "Van Lanschot Kempen",
                    position = "Expert Android developer",
                    startDate = "22.08.2022",
                    endDate = null
                )
            ),
            education = listOf(
                Education(
                    placeName = "Dnipropetrovsk National University",
                    education = "Master Degree in Computer Science",
                    startDate = "01.09.2008",
                    endDate = "01.06.2009"
                )
            ),
            avatar = null
        )

        val json = """
            {
              "firstName": "Anton",
              "lastName": "Valiukh",
              "experience": [
                {
                  "companyName": "Van Lanschot Kempen",
                  "position": "Expert Android developer",
                  "startDate": "22.08.2022",
                  "endDate": null
                }
              ],
              "education": [
                {
                  "placeName": "Dnipropetrovsk National University",
                  "education": "Master Degree in Computer Science",
                  "startDate": "01.09.2008",
                  "endDate": "01.06.2009"
                }
              ],
              "avatar": null
            }
        """

        val inputStream = ByteArrayInputStream(json.toByteArray())
        Mockito.`when`(context.assets.open("profile.json")).thenReturn(inputStream)

        val result = profileReader.getProfile()

        assertEquals(expectedProfile, result)
    }

    @Test(expected = Exception::class)
    fun `getProfile throws exception when file is not found`(): Unit = runBlocking {
        Mockito.`when`(context.assets.open("profile.json")).thenThrow(FileNotFoundException("File not found"))
        profileReader.getProfile()
    }
}
