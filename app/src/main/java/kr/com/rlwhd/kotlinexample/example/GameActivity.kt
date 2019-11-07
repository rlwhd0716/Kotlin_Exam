package kr.com.rlwhd.kotlinexample.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import kr.com.rlwhd.kotlinexample.R
import org.jetbrains.anko.db.INTEGER

class GameActivity : AppCompatActivity() {
    private var num = 0
    private var index = 0
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        bt_369.setOnClickListener {
            num = et_369.text.toString().toInt()

            for (i in 1..num) {
                val number = i.toString()
                val numArray = number.split("")
                for(j in numArray) {
                    if (j == "3" || j == "6" || j == "9") {
                        index++
                    }
                }
            }
            tv_369.text = index.toString()
            index = 0
        }

        bt_password.setOnClickListener {
            password = et_password.text.toString()

            val pass = password.split("")

//            for(i in pass) {
//                if(i == i)
//            }

            tv_password.text = password
        }
    }
}
