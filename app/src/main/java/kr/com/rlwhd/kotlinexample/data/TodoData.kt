package kr.com.rlwhd.kotlinexample.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TodoData(
    @PrimaryKey var id: Long = 0,
    var title: String = "",
    var date: Long = 0
) : RealmObject() {
}