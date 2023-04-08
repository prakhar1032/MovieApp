package com.app.movieapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.prob.ResultX
import com.app.movieapp.prob.trending_movies
import com.app.movieapp.prob.upcoming_movies
import com.app.movieapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel:ViewModel() {
    private var movieLiveData = MutableLiveData<List<com.app.movieapp.prob.Result>>()
    private var UpcomigMovieLiveData = MutableLiveData<List<ResultX>>()
    fun getTrendingMovies(){
        RetrofitInstance.api.getTrendingMovies("cff8d5f7c854bb6facb05f199209d6b7")
            .enqueue(object :Callback<trending_movies>{
                override fun onResponse(
                    call: Call<trending_movies>,
                    response: Response<trending_movies>
                ) {

                    if (response.body()!=null){
                        movieLiveData.value  = response.body()!!.results

                    }

                }

                override fun onFailure(call: Call<trending_movies>, t: Throwable) {
                    Log.d("TAG",t.message.toString())
                }

            })
    }

    fun getUpcomigMovies(){
        RetrofitInstance.api.getUpcomingMovies("cff8d5f7c854bb6facb05f199209d6b7")
            .enqueue(object :Callback<upcoming_movies>{
                override fun onResponse(
                    call: Call<upcoming_movies>,
                    response: Response<upcoming_movies>
                ) {

                    if(response.body()!=null){
                        UpcomigMovieLiveData.value = response.body()!!.results
//                        Log.d("result",response.body()!!.results.toString())


                    }
                }

                override fun onFailure(call: Call<upcoming_movies>, t: Throwable) {
                    Log.d("TAG",t.message.toString())
                }


            })
    }

    fun observeMovieLiveData():LiveData<List<com.app.movieapp.prob.Result>>{
        return movieLiveData
    }

    fun observeUpcomingMOvieLIveData():LiveData<List<ResultX>>{
        return UpcomigMovieLiveData
    }


}