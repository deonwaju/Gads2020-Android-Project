package gads2020.damolaolarewaju.testprojectgads.networking.repositories

import gads2020.damolaolarewaju.testprojectgads.models.LearningLeaderModel
import gads2020.damolaolarewaju.testprojectgads.models.ResourceModel
import gads2020.damolaolarewaju.testprojectgads.models.IQSkillLeaderModel

interface HomeRepository {
    suspend fun fetchLearningLeaders(): ResourceModel<List<LearningLeaderModel>>
    suspend fun fetchIQLeaders(): ResourceModel<List<IQSkillLeaderModel>>
    suspend fun submitProject(
        firstName: String, lastName: String, email: String, projectLink: String
    ): ResourceModel<Int>
}