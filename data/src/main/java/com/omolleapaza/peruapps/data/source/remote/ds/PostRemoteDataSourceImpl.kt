package com.omolleapaza.peruapps.data.source.remote.ds

import com.omolleapaza.peruapps.data.base.CallServiceBase
import com.omolleapaza.peruapps.data.source.remote.response.BasePost
import com.omolleapaza.peruapps.data.source.remote.service.RestService
import com.omolleapaza.peruapps.data.utils.ConnectionUtils
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure


class PostRemoteDataSourceImpl(
    private val restService: RestService,
    private val connectionUtils: ConnectionUtils
): PostRemoteDataSource, CallServiceBase() {

    override fun getNetworkUtils() = connectionUtils

    override suspend fun getPost(numberPage: Int): Either<Failure, BasePost> {
        return callService {
            restService.getPost(numberPage)
        }
    }

}