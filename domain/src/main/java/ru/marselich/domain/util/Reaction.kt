package ru.marselich.domain.util

import java.io.Serializable

sealed class Reaction<T>(
    val data: T? = null,
    val message: String? = null
): Serializable {
    class Success<T>(data: T): Reaction<T>(data)
    class Loading<T>(data: T? = null): Reaction<T>(data)
    class Error<T>(message: String, data: T? = null): Reaction<T>(data, message)

}
