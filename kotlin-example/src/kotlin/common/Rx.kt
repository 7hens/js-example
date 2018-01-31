package common

import modules.Rx

fun <T> Rx.Observable<T>.doOnError(onError: (Throwable) -> Unit): Rx.Observable<T> {
    return `do`({}, onError, {})
}

fun <T> Rx.Observable<T>.doOnComplete(onComplete: () -> Unit): Rx.Observable<T> {
    return `do`({}, {}, onComplete)
}