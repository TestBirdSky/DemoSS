package com.skybird.demoss.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.shadowsocks.Core
import com.github.shadowsocks.database.KeyValuePair
import com.github.shadowsocks.database.PrivateDatabase
import com.github.shadowsocks.database.Profile
import com.github.shadowsocks.utils.Key
import com.skybird.demoss.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Date：2022/9/20
 * Describe:
 */
@Database(entities = [User::class], version = 1)
abstract class RoomHelp : RoomDatabase() {
    companion object {
        private val instance by lazy {
            Room.databaseBuilder(App.mApp, RoomHelp::class.java, "db").apply {
                allowMainThreadQueries()
                enableMultiInstanceInvalidation()
                fallbackToDestructiveMigration()
                setQueryExecutor { GlobalScope.launch { it.run() } }
            }.build()
        }

        val userDao get() = instance.userDao()
    }

    abstract fun userDao(): UserDao

}