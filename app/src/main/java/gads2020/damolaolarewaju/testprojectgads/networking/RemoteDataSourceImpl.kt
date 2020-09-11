package gads2020.damolaolarewaju.testprojectgads.networking

import gads2020.damolaolarewaju.testprojectgads.models.LearningLeaderModel
import gads2020.damolaolarewaju.testprojectgads.models.ResourceModel
import gads2020.damolaolarewaju.testprojectgads.models.IQSkillLeaderModel
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService): RemoteDataSource {
    override suspend fun getTopLearningLeaders(): ResourceModel<List<LearningLeaderModel>> =
        try {
            val result = apiService.getTopLearningLeaders()
           ResourceModel.Success(result)
        }
        catch (error: Throwable){
            Timber.d(error.message.toString())
            ResourceModel.Error("Server error encountered!")
        }

    override suspend fun getTopIQLeaders(): ResourceModel<List<IQSkillLeaderModel>> =
        try {
            val result = apiService.getTopIQLeaders()
            ResourceModel.Success(result.body()!!)
        }
        catch (error: Throwable){
            Timber.d(error.message.toString())
            ResourceModel.Error("Server error encountered!")
        }

    override suspend fun submitProject(
        firstName: String,
        lastName: String,
        emailAddress: String,
        projectLink: String
    ): ResourceModel<Int> =
        try {
            val result = apiService.submitProject(
                firstName = firstName, lastName = lastName,
                emailAddress = emailAddress, projectLink = projectLink
            ).code()
            Timber.d(result.toString())
            if (result == 200){
                ResourceModel.Success(result)
            }
            else {
                Timber.d(result.toString())
                ResourceModel.Error("Submission not successful", null)
            }
        }
        catch (error: Throwable){
            ResourceModel.Error(error.message.toString(), null)
        }
}