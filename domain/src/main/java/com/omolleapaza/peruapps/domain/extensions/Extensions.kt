package com.omolleapaza.peruapps.domain.extensions

import com.omolleapaza.peruapps.domain.utils.Either

fun <R> R.toSuccess(): Either.Success<R> {
    return Either.Success(this)
}

fun <L> L.toError(): Either.Error<L> {
    return Either.Error(this)
}
 