package kr.com.rlwhd.kotlinexample.example

open class Person(var name: String): Runnable {
    override fun run(s: String) {
        name = s
    }

    override fun run() {
        println("달린다")
    }

    init {
        print(name)
    }

    val a = 1
    private val b = 2
    protected val c = 3
    internal val d = 4
}