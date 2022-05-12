package com.omolleapaza.peruapps.data.source.remote.ds

import com.omolleapaza.peruapps.data.source.remote.response.BasePost
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure


interface PostRemoteDataSource {

    suspend fun getPost(numberPage:Int) : Either<Failure, BasePost>

}