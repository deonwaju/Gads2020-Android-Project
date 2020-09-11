package gads2020.damolaolarewaju.testprojectgads.networking

import gads2020.damolaolarewaju.testprojectgads.models.LearningLeaderModel
import gads2020.damolaolarewaju.testprojectgads.models.ResourceModel
import gads2020.damolaolarewaju.testprojectgads.models.IQSkillLeaderModel

/**
 * Holds decoupled logic for api calls
 */
interface RemoteDataSource {
    suspend fun getTopLearningLeaders(): ResourceModel<List<LearningLeaderModel>>
    suspend fun getTopIQLeaders(): ResourceModel<List<IQSkillLeaderModel>>
    suspend fun submitProject(
        firstName: String,
        lastName: String,
        emailAddress: String,
        projectLink: String
    ): ResourceModel<Int>
}