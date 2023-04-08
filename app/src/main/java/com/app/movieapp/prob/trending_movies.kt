package com.app.movieapp.prob

data class trending_movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)