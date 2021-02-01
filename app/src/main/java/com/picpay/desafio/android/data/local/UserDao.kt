package com.picpay.desafio.android.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllUsers(users: List<User>)
}