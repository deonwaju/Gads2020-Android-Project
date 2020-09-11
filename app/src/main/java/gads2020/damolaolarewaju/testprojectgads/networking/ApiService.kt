package gads2020.damolaolarewaju.testprojectgads.networking

import retrofit2.Response
import retrofit2.http.*
import gads2020.damolaolarewaju.testprojectgads.models.LearningLeaderModel
import gads2020.damolaolarewaju.testprojectgads.models.IQSkillLeaderModel

/**
 * Retrofit powered api calls
 */
private const val SUBMIT_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse"
interface ApiService {
    @GET("/api/hours")
    suspend fun getTopLearningLeaders(): List<LearningLeaderModel>
    @GET("/api/skilliq")
    suspend fun getTopIQLeaders(): Response<List<IQSkillLeaderModel>>

    @POST
    @FormUrlEncoded
    suspend fun submitProject(
        @Url url: String = SUBMIT_URL,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.284483984") projectLink: String
    ): Response<Void>
}