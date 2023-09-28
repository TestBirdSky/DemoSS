package com.skybird.demoss.database

import androidx.room.Dao
import androidx.room.Insert

/**
 * Date：2022/9/20
 * Describe:
 */
@Dao
interface UserDao {
    @Insert
    fun add(user: User)
}