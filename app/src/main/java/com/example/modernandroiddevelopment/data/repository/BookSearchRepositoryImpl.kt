package com.example.modernandroiddevelopment.data.repository

import androidx.lifecycle.LiveData
import com.example.modernandroiddevelopment.data.api.RetrofitInstance.api
import com.example.modernandroiddevelopment.data.db.BookSearchDatabase
import com.example.modernandroiddevelopment.data.model.Book
import com.example.modernandroiddevelopment.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl(
    private val db: BookSearchDatabase
): BookSearchRepository {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<SearchResponse> {
        return api.searchBooks(query, sort, page, size)
    }

    override suspend fun insertBooks(book: Book) {
        db.bookSearchDao().insertBook(book)
    }

    override suspend fun deleteBooks(book: Book) {
        db.bookSearchDao().deleteBook(book)
    }

    override fun getFavoriteBooks(): LiveData<List<Book>> {
        return db.bookSearchDao().getFavoriteBooks()
    }
}