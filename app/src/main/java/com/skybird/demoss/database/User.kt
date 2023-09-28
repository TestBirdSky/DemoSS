package com.skybird.demoss.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Date：2022/9/20
 * Describe:
 */
@Entity
class User {
    @PrimaryKey
    var id = 0
    var name:String=""
}