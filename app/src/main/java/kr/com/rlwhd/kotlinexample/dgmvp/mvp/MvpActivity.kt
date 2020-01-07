package kr.com.rlwhd.kotlinexample.dgmvp.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mvp.*
import kr.com.rlwhd.kotlinexample.R

class MvpActivity : AppCompatActivity(), MvpConstants.View {
    private val mvpPresenter = MvpPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)

        initListener()
    }

    private fun initListener() {
        bt_mvp_calc.setOnClickListener {
            val num1 = et_mvp_num1.text.toString().toInt()
            val num2 = et_mvp_num2.text.toString().toInt()
            mvpPresenter.addNums(num1, num2)
        }
    }

    override fun showResult(result: Int) {
        tv_mvp_result.text = result.toString()
    }
}
