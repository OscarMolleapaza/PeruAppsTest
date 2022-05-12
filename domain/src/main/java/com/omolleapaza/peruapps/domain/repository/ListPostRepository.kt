package com.omolleapaza.peruapps.domain.repository

import com.omolleapaza.peruapps.domain.model.BasePost
import com.omolleapaza.peruapps.domain.model.PostDeleted
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure



interface ListPostRepository {

    suspend fun getPost(actualPage:Int) : Either<Failure, BasePost>

    suspend fun postDeleted(deleted: PostDeleted): Either<Failure, Unit>

}