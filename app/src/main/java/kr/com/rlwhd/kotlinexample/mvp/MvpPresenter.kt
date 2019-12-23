package kr.com.rlwhd.kotlinexample.mvp

class MvpPresenter(view: MvpConstants.View): MvpConstants.Presenter {
    private val mainView: MvpConstants.View = view
    private val mainModel = MvpModel(this)

    override fun addNums(num1: Int, num2: Int) {
        mainView.showResult(num1 + num2)
    }

    override fun saveData(data: Int) {
        mainModel.saveData(data)
    }
}