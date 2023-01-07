package com.example.abcd.modelclasses

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)