package com.app.movieapp.retrofit

import com.app.movieapp.prob.trending_movies
import com.app.movieapp.prob.upcoming_movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("popular?")
    fun getTrendingMovies(@Query("api_key")api_key:String) :Call<trending_movies>

    @GET("upcoming?")
    fun getUpcomingMovies(@Query("api_key")api_key:String) :Call<upcoming_movies>
}