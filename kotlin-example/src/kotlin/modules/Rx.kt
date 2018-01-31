package modules

import kotlin.js.Promise

@JsModule("rxjs")
external object Rx {
    open class Subscription(unsubscribe: () -> Unit) {
        val closed: Boolean
        fun add(tearDown: () -> Unit): Subscription
        fun remove(subscription: Subscription)
        fun unsubscribe()
    }

    open class Action<T>(scheduler: Scheduler, work: (state: T) -> Unit) : Subscription {
        fun schedule(state: T, delay: Long = definedExternally): Subscription
    }

    open class Subscriber<T>(next: (T) -> Unit, error: (Throwable) -> Unit, complete: () -> Unit) : Subscription {
        fun complete()
        fun error(e: Throwable)
        fun next(value: T)

        companion object {
            fun <T> create(next: (T) -> Unit, error: (Throwable) -> Unit, complete: () -> Unit): Subscriber<T>
        }
    }

    open class SubjectSubscriber<T> : Subscriber<T>

    open class Observable<T> {
        fun <R> catch(selector: (Throwable) -> Observable<R>): Observable<R>
        fun delay(delay: Number, scheduler: Scheduler = definedExternally): Observable<T>
        fun dematerialize(): Observable<T>
        fun `do`(next: (T) -> Unit, error: (Throwable) -> Unit, complete: () -> Unit = definedExternally): Observable<T>
        fun `do`(observer: Observer<T>): Observable<T>
        fun finally(complete: () -> Unit): Observable<T>
        @JsName("do") fun doOnNext(next: (T) -> Unit): Observable<T>
        fun <R> flatMap(project: (T) -> Observable<R>): Observable<R>
        @JsName("flatMap") fun <R> flatMapIndexed(project: (T, Int) -> Observable<R>): Observable<R>
        @JsName("map") fun <R> mapIndexed(project: (value: T, index: Int) -> R): Observable<R>
        fun <R> map(project: (T) -> R): Observable<R>
        fun <R> mapTo(value: R): Observable<R>
        fun isEmpty(): Observable<Boolean>
        fun take(count: Int): Observable<T>
        fun takeLast(count: Int): Observable<T>
        fun takeUntil(notifier: Observable<out Any>): Observable<T>
        fun takeWhile(predicate: (T) -> Boolean): Observable<T>
        @JsName("takeWhile") fun takeWhileIndexed(predicate: (T, Int) -> Boolean): Observable<T>
        fun subscribe(observer: Observer<T>): Subscription
        fun subscribe(next: (T) -> Unit, error: (Throwable) -> Unit, complete: () -> Unit = definedExternally): Subscription
        fun subscribe(next: (T) -> Unit, error: (Throwable) -> Unit): Subscription
        fun subscribe(next: (T) -> Unit): Subscription
        fun subscribe(): Subscription

        companion object {
            fun <T> concat(vararg observable: Observable<T>, scheduler: Scheduler = definedExternally): Observable<T>
            fun <T> create(onSubscription: (Observer<T>) -> Unit): Observable<T>
            fun <T> empty(scheduler: Scheduler): Observable<T>
            fun <T> fromPromise(promise: Promise<T>, scheduler: Scheduler): Observable<T>
            fun interval(period: Double, scheduler: Scheduler): Observable<Long>
            fun <T> never(): Observable<T>
            fun <T> of(vararg values: T, scheduler: Scheduler = definedExternally): Observable<T>
            @JsName("throw") fun <T> error(e: Throwable, scheduler: Scheduler = definedExternally): Observable<T>
            fun timer(initialDelay: Long, period: Long, scheduler: Scheduler = definedExternally): Observable<Long>
        }
    }

    interface Observer<T> {
        val closed: Boolean
        fun next(value: T)
        fun error(err: Throwable)
        fun complete()
    }

    open class Scheduler {
        fun now(): Long
        fun <T> schedule(work: (T) -> Subscription, delay: Double, state: T): Subscription

        companion object {
            val queue: Scheduler
            val asap: Scheduler
            val async: Scheduler
            val animationFrame: Scheduler
        }
    }

    object Symbol {
        val rxSubscriber: String
        val observable: String
        val iterator: String
    }

    open class ConnectableObservable<T> : Observable<T>
    open class GroupedObservable<T> : Observable<T>

    open class Subject<T> : Observable<T>
    open class ReplaySubject<T> : Subject<T>
    open class BehaviorSubject<T> : Subject<T>
    open class AsyncSubject<T> : Subject<T>
    open class AnonymousSubject<T> : Subject<T>
}
