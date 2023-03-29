package com.mr.touristguide.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)

    //    class Loading<T>(data: T? = null): Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: () -> Boolean = { true }
) = flow {
    val flow = if (shouldFetch()) {
//        emit(Resource.Loading(null))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (e: Exception) {
            e.printStackTrace()
            query().map {
                Resource.Error(
                    data = it,
                    message = "An error occurred in networkBoundResource"
                )
            }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}
