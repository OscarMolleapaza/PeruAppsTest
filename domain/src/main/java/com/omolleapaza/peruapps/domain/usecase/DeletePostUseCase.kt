package com.omolleapaza.peruapps.domain.usecase

import com.omolleapaza.peruapps.domain.base.BaseUseCase
import com.omolleapaza.peruapps.domain.model.PostDeleted
import com.omolleapaza.peruapps.domain.repository.ListPostRepository
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure




class DeletePostUseCase(
    private val repository: ListPostRepository
) :
    BaseUseCase<Unit, DeletePostUseCase.Params>() {
    data class Params(
        val id: Int,
        val date: String,
    )

    override suspend fun run(params: Params): Either<Failure, Unit> {
        return repository.postDeleted(
            PostDeleted(
                params.id,
                params.date
            )
        )

    }


}