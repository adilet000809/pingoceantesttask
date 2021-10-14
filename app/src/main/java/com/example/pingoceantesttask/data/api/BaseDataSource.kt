package com.example.pingoceantesttask.data.api

import com.example.pingoceantesttask.data.model.Result
import com.example.pingoceantesttask.data.util.ErrorUtil
import retrofit2.Response
import retrofit2.Retrofit

open class BaseDataSource constructor(
    private val retrofit: Retrofit
) {

    protected suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtil.parseError(result, retrofit)
                Result.error(errorResponse?.message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }

}