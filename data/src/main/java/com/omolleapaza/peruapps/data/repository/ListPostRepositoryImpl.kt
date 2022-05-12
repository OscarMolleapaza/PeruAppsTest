package com.omolleapaza.peruapps.data.repository

import com.omolleapaza.peruapps.data.mapper.ListPostMapper
import com.omolleapaza.peruapps.data.source.local.ds.PostLocalDataSource
import com.omolleapaza.peruapps.data.source.remote.ds.PostRemoteDataSource
import com.omolleapaza.peruapps.data.utils.ConnectionUtils
import com.omolleapaza.peruapps.domain.model.BasePost
import com.omolleapaza.peruapps.domain.model.PostDeleted
import com.omolleapaza.peruapps.domain.repository.ListPostRepository
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure




class ListPostRepositoryImpl(
    private val connection: ConnectionUtils,
    private val remoteDs: PostRemoteDataSource,
    private val localDS: PostLocalDataSource,
    private val dataMapper: ListPostMapper
) : ListPostRepository {


    override suspend fun getPost(actualPage: Int): Either<Failure, BasePost> {
        if (connection.isNetworkAvailable()) {

            return when (val response = remoteDs.getPost(actualPage)) {
                is Either.Success -> {

                    localDS.deleteAllPost()
                    val listData = dataMapper.mapPostResponseToModel(response.success)

                    val listEntity =
                        dataMapper.mapPostResponseToEntity(response.success).toMutableList()

                    val listMapperComplete = listData.listPost.mapIndexed { index, post ->
                        val status = !localDS.isPostDeleted(post.story_id.toString())
                        listEntity[index].visible = status
                        post.copy(
                            visible = status
                        )
                    }

                    localDS.saveListPosts(listEntity)

                    Either.Success(
                        listData.copy(
                            listPost = listMapperComplete
                        )
                    )
                }
                is Either.Error -> Either.Error(response.error)
                else -> Either.Error(Failure.None)
            }
        } else {
            return if (actualPage == 0) {
                when (val response = localDS.getAllPost()) {
                    is Either.Success -> {
                        val temp = dataMapper.mapEntityPostToModel(response.success)
                        Either.Success(temp)
                    }
                    is Either.Error -> Either.Error(response.error)
                    else -> Either.Error(Failure.None)
                }
            } else {
                Either.Error(Failure.NoNetworkDetected)
            }

        }
    }

    override suspend fun postDeleted(deleted: PostDeleted): Either<Failure, Unit> {
        localDS.removeAllDataOld()
        return localDS.savePostDeleted(response = dataMapper.mapPostDeletedToEntity(deleted))
    }

}

