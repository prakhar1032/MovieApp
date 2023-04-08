package com.app.movieapp.prob

data class upcoming_movies(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)