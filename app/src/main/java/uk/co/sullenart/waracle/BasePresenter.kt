package uk.co.sullenart.waracle

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter {
    private val compositeDisposable = CompositeDisposable()

    protected fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun stop() {
        compositeDisposable.clear()
    }
}