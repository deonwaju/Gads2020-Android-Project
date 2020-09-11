package gads2020.damolaolarewaju.testprojectgads.networking.repositories

import gads2020.damolaolarewaju.testprojectgads.models.LearningLeaderModel
import gads2020.damolaolarewaju.testprojectgads.models.ResourceModel
import gads2020.damolaolarewaju.testprojectgads.models.IQSkillLeaderModel
import gads2020.damolaolarewaju.testprojectgads.networking.RemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl
@Inject constructor(private val remoteDataSource: RemoteDataSource):
    HomeRepository {
    override suspend fun fetchLearningLeaders(): ResourceModel<List<LearningLeaderModel>> =
        remoteDataSource.getTopLearningLeaders()

    override suspend fun fetchIQLeaders(): ResourceModel<List<IQSkillLeaderModel>> =
        remoteDataSource.getTopIQLeaders()

    override suspend fun submitProject(
        firstName: String, lastName: String, email: String, projectLink: String
    ): ResourceModel<Int> =
        remoteDataSource.submitProject(firstName, lastName, email, projectLink)
}