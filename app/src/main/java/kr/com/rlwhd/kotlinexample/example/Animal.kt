package kr.com.rlwhd.kotlinexample.example

open class Animal(var name: String) {


    inner class Cat {
        fun naming() {
            name = "고양이"
        }
    }

}