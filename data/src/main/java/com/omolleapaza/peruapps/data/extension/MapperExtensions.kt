package com.omolleapaza.peruapps.data.extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



/**
 * Ensures that the mapper [block] inside the inline function be on
 * the context of [Dispatchers.IO].
 *
 * Any [T] object can extend this function and the operation
 * inside the block will return a new object.
 */
suspend inline fun <T, R> T.mapTo(crossinline block: suspend (T) -> R) : R {
    return withContext(Dispatchers.IO) {
        return@withContext block.invoke(this@mapTo)
    }
}