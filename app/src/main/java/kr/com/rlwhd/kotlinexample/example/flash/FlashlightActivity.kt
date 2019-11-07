package kr.com.rlwhd.kotlinexample.example.flash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_flashlight.*
import kr.com.rlwhd.kotlinexample.R
import org.jetbrains.anko.intentFor

class FlashlightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashlight)

        val torch = Torch(this)

        sw_flash.setOnCheckedChangeListener{ buttonView, isChecked ->
            /*if (isChecked) {
                torch.flashOn()
            } else {
                torch.flashOff()
            }*/

            if (isChecked) {
                startService(intentFor<TorchService>().setAction("on"))
            } else {
                startService(intentFor<TorchService>().setAction("off"))
            }
        }
    }
}
