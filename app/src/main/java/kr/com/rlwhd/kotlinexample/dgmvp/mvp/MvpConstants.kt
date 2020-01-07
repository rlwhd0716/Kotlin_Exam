package kr.com.rlwhd.kotlinexample.dgmvp.mvp

interface MvpConstants {
    interface View {
        fun showResult(result: Int)
    }

    interface Presenter {
        fun addNums(num1: Int, num2: Int)

        fun saveData(data: Int)
    }
}