package com.example.starwars.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.starwars.utils.Result
import kotlinx.coroutines.Dispatchers

fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: (A) -> Unit
): LiveData<Result<T>> = liveData(Dispatchers.IO) {
    emit(Result.loading<T>())
    val source = databaseQuery.invoke().map { Result.success(it) }
    emitSource(source)

    val responseStatus = networkCall.invoke()
    if (responseStatus.status == Result.Status.SUCCESS) {
        saveCallResult(responseStatus.data!!)
    } else if (responseStatus.status == Result.Status.ERROR) {
        emit(Result.error<T>(responseStatus.message!!))
        emitSource(source)
    }
}