package kr.com.rlwhd.kotlinexample.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kr.com.rlwhd.kotlinexample.data.User

@Dao
interface UserDAO {
    @Insert
    fun insertUsers(vararg users: User)

    @Delete
    fun dleleteUsers(vararg users: User)

    @Query("SELECT * FROM users")
    fun loadAllUsers(): List<User>

//    @Query("SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge")
//    fun loadAllUserBetweenAges(minAge: Int, maxAge: Int): Array<User>
//
//    @Query("SELECT firstName, lastName FROM users WHERE region IN (:regions)")
//    fun loadusersFromRegions(regions: List<String>): List<NameTuple>
}