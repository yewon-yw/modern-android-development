package com.example.modernandroiddevelopment.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.modernandroiddevelopment.data.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
    // CUD 작업은 시간이 걸리는 작업이기 때문에 코루틴 안에서 비동기적 실행 필요해서 suspend 함수로 작성해줌

    @Query("SELECT * FROM books")
    fun getFavoriteBooks(): Flow<List<Book>>
}