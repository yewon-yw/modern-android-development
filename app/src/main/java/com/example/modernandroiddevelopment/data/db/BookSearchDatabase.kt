package com.example.modernandroiddevelopment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.modernandroiddevelopment.data.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(OrmConverter::class)
abstract class BookSearchDatabase: RoomDatabase() {

    abstract fun bookSearchDao(): BookSearchDao

    companion object {
        // 데이터 베이스 객체도 싱글톤으로 설정해줌
        @Volatile
        private var INSTANCE: BookSearchDatabase? = null

        private fun buildDatabase(context: Context): BookSearchDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                BookSearchDatabase::class.java,
                "favorite-books"
            ).build()

        fun getInstance(context: Context): BookSearchDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
    }
}