package kr.com.rlwhd.kotlinexample.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dagger.*
import kr.com.rlwhd.kotlinexample.R
import kr.com.rlwhd.kotlinexample.dagger.model.Cat
import kr.com.rlwhd.kotlinexample.dagger.model.Dog
import kr.com.rlwhd.kotlinexample.dagger.module.CatModule
import kr.com.rlwhd.kotlinexample.dagger.module.DogModule
import javax.inject.Inject

class DaggerActivity : AppCompatActivity() {
    @Inject lateinit var cat : Cat
    @Inject lateinit var dog : Dog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        injectComponent()

        setGetCatNameButton()
        setGetDogNameButton()
    }

    private fun injectComponent() {
        DaggerPetComponent.builder()
            .catModule(CatModule)
            .dogModule(DogModule)
            .build()
            .inject(this)
    }

    private fun setGetCatNameButton() {
        bt_cat_name.setOnClickListener {
            tv_daager_test.text = cat.getCatName()
        }
    }

    private fun setGetDogNameButton() {
        bt_dog_name.setOnClickListener {
            tv_daager_test.text = dog.getDaogName()
        }
    }
}
