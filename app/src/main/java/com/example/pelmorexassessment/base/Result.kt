package com.example.pelmorexassessment.base

import androidx.annotation.Keep
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import java.lang.reflect.Type

sealed class Result<out R> {

    data class Success<out T>(val data: T, val url: String? = null) : Result<T>()
    data class Failure(val error: Error) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[error=$error]"
        }
    }

    inline fun onSuccess(block: (R) -> Unit): Result<R> {
        if (this is Success) {
            (data as? R)?.also {
                block(it)
            }
        }
        return this
    }

    inline fun onFailure(block: (Error) -> Unit): Result<R> {
        if (this is Failure) {
            block(error)
        }
        return this
    }

}

data class Error(
    val statusCode: Int,
    override val message: String,
    val status: String,
    var title: String? = null
) : Throwable() {
    companion object {
        const val ERROR_CODE_NETWORK_ERROR = "NETWORK_ERROR"
        const val ERROR_TIMEOUT_ERROR = "ERROR_TIMEOUT_ERROR"

        val NetworkError = Error(0, ERROR_CODE_NETWORK_ERROR, "")
        val TimeoutError = Error(-100, ERROR_TIMEOUT_ERROR, "")


        fun error(message: String) = Error(-1, message, "")

        fun parseError(body: ResponseBody): Error {
            return try {
                val builder = GsonBuilder()
                val type = object : TypeToken<ErrorInfo>() {}.type
                builder.registerTypeAdapter(type, ErrorInfoJsonDeserializer())
                val gson = builder.create()
                val errorInfo = gson.fromJson(body.string(), ErrorInfo::class.java)
                Error(errorInfo.code ?: -1, errorInfo.message ?: "", errorInfo.status ?: "")
            } catch (e: Exception) {
                Error(400, "", "")
            }
        }
    }

    @Keep
    data class ErrorInfo(
        val code: Int,
        val message: String?,
        val status: String?,
    )

    class ErrorInfoJsonDeserializer : JsonDeserializer<ErrorInfo> {
        override fun deserialize(
            json: JsonElement,
            typeOfT: Type,
            context: JsonDeserializationContext
        ): ErrorInfo {
            if (json.isJsonObject) {
                val obj = json.asJsonObject
                val statusCode = obj.get("code")?.asInt ?: 400
                var errorMessage = obj.get("message")?.asString ?: ""
                val status = obj.get("status")?.asString ?: ""
                return ErrorInfo(
                    statusCode,
                    errorMessage,
                    status
                )
            }
            throw JsonParseException("not json object")
        }

    }
}