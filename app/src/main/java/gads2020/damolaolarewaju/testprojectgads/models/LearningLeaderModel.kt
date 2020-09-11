package gads2020.damolaolarewaju.testprojectgads.models

import com.squareup.moshi.Json

data class LearningLeaderModel (
    @field:Json(name = "name") val name: String,
    @field:Json(name = "hours") val hours: Int,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "badgeUrl") val badgeUrl: String
)