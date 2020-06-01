package kr.com.rlwhd.kotlinexample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val userName: String,
    val firstName: String,
    val lastName: String) {

}