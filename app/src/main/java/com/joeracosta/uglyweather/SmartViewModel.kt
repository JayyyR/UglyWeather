package com.joeracosta.uglyweather

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Joe on 12/26/2017.
 */

abstract class SmartViewModel : BaseObservableViewModel() {

    val alertUserSubject = PublishSubject.create<Int>()

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun removeDisposable(disposable: Disposable){
        disposables.remove(disposable)
    }

    fun Disposable.addToComposite() {
        disposables.add(this)
    }

}

