package com.example.pelmorexassessment.base

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.example.pelmorexassessment.base.Result

suspend fun <T> Call<T>.enqueue(): Result<T> = suspendCoroutine { continuation ->
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    continuation.resume(Result.Success(body))
                } else {
                    continuation.resume(Result.Failure(Error.NetworkError))
                }
            } else {
                val body = response.errorBody()
                if (body != null) {
                    val error = Error.parseError(body)
                    continuation.resume(Result.Failure(error))
                } else {
                    continuation.resume(Result.Failure(Error.NetworkError))
                }

            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            if (t is SocketTimeoutException) {
                continuation.resume(Result.Failure(Error.TimeoutError))
            } else if (t is UnknownHostException) {
                continuation.resume(Result.Failure(Error.NetworkError))
            } else {
                continuation.resume(
                    Result.Failure(
                        Error.error(
                            t.localizedMessage ?: ""
                        )
                    )
                )
            }
        }
    }
    )
}

suspend fun <T, R> Call<T>.enqueue(onSuccess: suspend (T) -> R): Result<R> {
    return when (val result = this.enqueue()) {
        is com.example.pelmorexassessment.base.Result.Success -> com.example.pelmorexassessment.base.Result.Success(
            onSuccess(result.data)
        )
        is com.example.pelmorexassessment.base.Result.Failure -> result
    }
}