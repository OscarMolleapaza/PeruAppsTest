package com.omolleapaza.peruapps.data.base

import com.omolleapaza.peruapps.data.utils.ConnectionUtils
import com.omolleapaza.peruapps.domain.utils.Either
import com.omolleapaza.peruapps.domain.utils.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException


abstract class CallServiceBase() {

    abstract fun getNetworkUtils(): ConnectionUtils

    /**
     * Use this for unit(void) responses returns.
     */
    protected suspend inline fun <T> callService(crossinline retrofitCall: suspend () -> Response<T>): Either<Failure, T> {
        return when (getNetworkUtils().isNetworkAvailable()) {
            true -> {
                try {
                    withContext(Dispatchers.IO) {
                        val response = retrofitCall.invoke()
                        if (response.isSuccessful && response.body() != null) {
                            return@withContext Either.Success(response.body()!!)
                        } else {
                            return@withContext Either.Error(getErrorMessageFromServer(
                                response.code(), null))
                        }
                    }
                } catch (e: Exception) {
                    return Either.Error(parseException(e))
                }
            }
            false -> Either.Error(Failure.NoNetworkDetected)
        }
    }

    /**
     * Parse Server Error to [Failure.ServerBodyError] if [errorBody].
     * @return [Failure] object.
     */
    suspend fun getErrorMessageFromServer(errorCode: Int?, errorBody: String?) : Failure {
        return if (!errorBody.isNullOrBlank()) {
            return withContext(Dispatchers.IO) {
                return@withContext when (errorCode) {
                    401 -> {
                        Failure.UnauthorizedOrForbidden(errorBody)
                    }
                    423 -> {
                        Failure.AppNotAvailable(errorBody)
                    }
                    else -> {
                        Failure.ServerBodyError(errorCode?:500, errorBody)
                    }
                }
            }
        } else {
            //No error body was found.
            Failure.None
        }
    }

    fun parseException(throwable: Throwable) : Failure {
        return when(throwable) {
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.ServiceUncaughtFailure(throwable.message?:"Service response doesn't match with response object.")
        }
    }

}