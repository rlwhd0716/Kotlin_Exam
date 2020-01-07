package kr.com.rlwhd.kotlinexample.dgmvp.di.presenter

import androidx.annotation.CallSuper

abstract class BasePresenter<T> {
    abstract var view: T?

    @CallSuper
    open fun onCreate() {
    }

    @CallSuper
    open fun onResume(){
    }

    @CallSuper
    open fun onPause() {
    }

    @CallSuper
    open fun onStop() {
    }

    @CallSuper
    fun onDestroy() {
        view = null
    }
}