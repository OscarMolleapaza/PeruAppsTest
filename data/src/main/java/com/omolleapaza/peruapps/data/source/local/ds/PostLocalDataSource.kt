package com.omolleapaza.peruapps.data.source.local.ds

import com.omolleapaza.peruapps.data.source.local.db.entity.PostDeletedEntity
import com.omolleapaza.peruapps.data.source.local.db.entity.PostEntity
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure


interface PostLocalDataSource {

    suspend fun getAllPost() : Either<Failure, List<PostEntity>>

    suspend fun saveListPosts(response: List<PostEntity>) : Either<Failure, Unit>

    suspend fun deletePost(response:PostEntity): Either<Failure, Unit>

    suspend fun deleteAllPost(): Either<Failure, Unit>

    suspend fun savePostDeleted(response: PostDeletedEntity): Either<Failure, Unit>

    suspend fun isPostDeleted(idPost:String): Boolean

    suspend fun removeAllDataOld()

}