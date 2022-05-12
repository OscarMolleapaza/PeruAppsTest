package com.omolleapaza.peruapps.domain.usecase

import com.omolleapaza.peruapps.domain.base.BaseUseCase
import com.omolleapaza.peruapps.domain.model.BasePost
import com.omolleapaza.peruapps.domain.repository.ListPostRepository
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure



class GetPostsUseCase(
    private val repository: ListPostRepository
) :
    BaseUseCase<BasePost, GetPostsUseCase.Params>() {
    data class Params(
        val page: Int,
    )

    override suspend fun run(params: Params): Either<Failure, BasePost> {
        return repository.getPost(params.page)

    }


}