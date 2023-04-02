package com.example.testapplication.data

//typealias Mapper<Input, Output> = (Input) -> Output
//
//sealed class ResultNetwork <T> {
//
////    fun <R> map(mapper: Mapper<T, R>? = null): ResultNetwork<R> = when(this) {
////        is PendingResult -> PendingResult()
////        is ErrorResult -> ErrorResult(this.exception)
////        is SuccessResult -> {
////            if (mapper == null) throw IllegalArgumentException(
////                "Mapper should not be null for success result")
////            SuccessResult(mapper(this.data))
////        }
////    }
////}
//
//class PendingResult<T> : ResultNetwork <T>()
//
//class SuccessResult<T> (
//    val data: T
//        ): ResultNetwork <T>()
//
//class ErrorResult<T>(
//    val exception: Exception
//): ResultNetwork <T>()
//
//fun <T> ResultNetwork <T>?.takeSuccess(): T? {
//    return if (this is SuccessResult)
//        this.data
//    else
//        null
//}