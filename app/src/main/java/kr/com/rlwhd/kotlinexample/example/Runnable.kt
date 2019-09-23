package kr.com.rlwhd.kotlinexample.example

interface Runnable {
    fun run()

    fun run(s: String)

    fun fastRun() = println("빨리 달린다")
}