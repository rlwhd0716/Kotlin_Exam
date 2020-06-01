package kr.com.rlwhd.kotlinexample.retrofit

data class TestData(
    val data: Accepted
)

data class Accepted(
    val accepted: HashMap<String, Friend>
)

data class Friend(
    val success: Boolean,
    val message: String
)