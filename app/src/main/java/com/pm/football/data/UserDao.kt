package com.pm.football.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pm.football.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM training_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM training_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}