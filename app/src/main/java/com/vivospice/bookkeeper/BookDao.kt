package com.vivospice.bookkeeper

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)
}