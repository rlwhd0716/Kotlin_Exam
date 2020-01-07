package kr.com.rlwhd.kotlinexample.dgmvp.di.view

import android.os.Bundle
import kr.com.rlwhd.kotlinexample.dgmvp.di.presenter.BasePresenter

abstract class PresenterActivity<T> : BaseActivity() {
    protected abstract fun getPresenter(): BasePresenter<T>?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresenter()?.onCreate()
    }

    override fun onResume() {
        super.onResume()
        getPresenter()?.onResume()
    }

    override fun onPause() {
        super.onPause()
        getPresenter()?.onPause()
    }

    override fun onStop() {
        super.onStop()
        getPresenter()?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }
}