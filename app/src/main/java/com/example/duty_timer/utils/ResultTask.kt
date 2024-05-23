package com.example.duty_timer.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

sealed class ResultTask<T> {

    /**
     * Convert ResultTask<T> into ResultTask<R>.
     */
    fun <R> map(mapper: ((T) -> R)? = null): ResultTask<R> {
        return when (this) {
            is SuccessTask<T> -> {
                if (mapper == null) {
                    throw IllegalStateException("Can't map SuccessTask<T> result without mapper.")
                } else {
                    SuccessTask(mapper(this.value))
                }
            }
            is ErrorTask<T> -> ErrorTask(this.error)
            is EmptyTask<T> -> EmptyTask()
            is PendingTask<T> -> PendingTask()
        }
    }

    fun getValueOrNull(): T? {
        if (this is SuccessTask<T>) return this.value
        return null
    }

    fun isFinished() = this is SuccessTask<T> || this is ErrorTask<T>
}

class SuccessTask<T>(
    val value: T
) : ResultTask<T>()

class ErrorTask<T>(
    val error: Throwable
) : ResultTask<T>()

class EmptyTask<T> : ResultTask<T>()

class PendingTask<T> : ResultTask<T>()

typealias MutableLiveResult<T> = MutableLiveData<ResultTask<T>>
typealias LiveResult<T> = LiveData<ResultTask<T>>
