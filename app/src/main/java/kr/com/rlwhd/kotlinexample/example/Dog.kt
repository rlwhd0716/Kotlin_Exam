package kr.com.rlwhd.kotlinexample.example

class Dog(name: String): Animal(name), Runnable {
    override fun run(s: String) {
        name = s
    }

    override fun run() {
        name = "강아지"
    }
}