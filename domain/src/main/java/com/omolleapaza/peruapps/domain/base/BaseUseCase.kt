package com.omolleapaza.peruapps.domain.base


import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure
import kotlinx.coroutines.*



abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
//        CoroutineExceptionHandler
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            onResult(Either.Error(Failure.DataToDomainMapperFailure(throwable.message)))
        }

        val backgroundJob = scope.async(Dispatchers.IO) { run(params) }
        scope.launch(exceptionHandler){ onResult(backgroundJob.await()) }
    }
}