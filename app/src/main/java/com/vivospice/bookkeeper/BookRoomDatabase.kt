package com.vivospice.bookkeeper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class BookRoomDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

     //create a singleton (one instance only) to acquire instance of DB using Room.databaseBuilder().
    companion object {

        private var bookRoomInstance: BookRoomDatabase? = null

        fun getDatabase(context: Context) : BookRoomDatabase? {
            if (bookRoomInstance  == null) {
                //create new instance of the database
                synchronized(BookRoomDatabase::class.java) {  //Create lock object to insure thread safty
                    if(bookRoomInstance == null) {
                        bookRoomInstance = Room.databaseBuilder<BookRoomDatabase>(context.applicationContext,
                            BookRoomDatabase::class.java, "book_database")
                            .build()
                    }
                }
            }
            return bookRoomInstance
        }
    }

}