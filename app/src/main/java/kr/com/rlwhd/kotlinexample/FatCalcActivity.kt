package kr.com.rlwhd.kotlinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fat_calc.*
import org.jetbrains.anko.toast

class FatCalcActivity : AppCompatActivity() {
    private var height = 0
    private var weight = 0
    var bmi = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fat_calc)

        bt_result.setOnClickListener {
            height = et_height.text.toString().toInt()
            weight = et_weight.text.toString().toInt()

            bmi = (weight / Math.pow(height / 100.0, 2.0))

            bmiLevel(bmi)
            bmiResult(bmi)
            toast("$bmi")
        }
    }

    private fun bmiLevel(bmi: Double) {
        when {
            bmi >= 35 -> tv_result.text = "고도비만"
            bmi >= 30 -> tv_result.text = "2단계비만"
            bmi >= 25 -> tv_result.text = "1단계비만"
            bmi >= 23 -> tv_result.text = "과체중"
            bmi >= 18.5 -> tv_result.text = "정상"
            else -> tv_result.text = "저체중"
        }
    }

    private fun bmiResult(bmi: Double) {
        when {
            bmi >= 23 -> iv_result.setImageResource(R.drawable.ic_very_dissatisfied_black_24dp)
            bmi >= 18.5 -> iv_result.setImageResource(R.drawable.ic_satisfied_black_24dp)
            else -> iv_result.setImageResource(R.drawable.ic_dissatisfied_black_24dp)
        }
    }
}
