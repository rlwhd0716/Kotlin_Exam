package kr.com.rlwhd.kotlinexample.dgmvp.di.presenter

import android.app.Activity
import kr.com.rlwhd.kotlinexample.ApplicationKt
import kr.com.rlwhd.kotlinexample.dgmvp.di.component.DaggerPresenterComponent
import kr.com.rlwhd.kotlinexample.dgmvp.di.module.ApiModule

class MainPresenter constructor(activity: Activity) : BasePresenter<MainPresenter.View>() {
    override var view: View? = activity as View

    init {
        DaggerPresenterComponent.builder()
            .appComponent(ApplicationKt.getAppComponent(activity))
            .apiModule(ApiModule())
            .build().inject(this)
    }

    interface View {
        fun showProgress()
        fun showMoreProgress()
        fun hideProgress()
        fun setAdapter()
//        fun setAdapter(repositories: ArrayList<Repository>)
    }

}