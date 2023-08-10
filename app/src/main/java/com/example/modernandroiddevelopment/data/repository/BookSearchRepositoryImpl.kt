package com.example.modernandroiddevelopment.data.repository

import com.example.modernandroiddevelopment.data.api.RetrofitInstance.api
import com.example.modernandroiddevelopment.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl: BookSearchRepository {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<SearchResponse> {
        return api.searchBooks(query, sort, page, size)
    }
}