package com.midtronics.data

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("experience") val experience: List<Experience>,
    @SerializedName("education") val education: List<Education>,
)

data class Experience(
    @SerializedName("company_name") val companyName: String,
    @SerializedName("position") val position: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("end_date") val endDate: String?
)

data class Education(
    @SerializedName("place_name") val placeName: String,
    @SerializedName("education") val education: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("end_date") val endDate: String?
)