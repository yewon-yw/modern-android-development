package com.example.modernandroiddevelopment.data.repository

import com.example.modernandroiddevelopment.data.model.SearchResponse
import retrofit2.Response

interface BookSearchRepository {
    suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<SearchResponse>
}