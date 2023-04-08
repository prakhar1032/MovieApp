package com.app.movieapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.movieapp.R
import com.app.movieapp.adapter.movieAdapter
import com.app.movieapp.adapter.upcomingAdapter
import com.app.movieapp.databinding.ActivityMainBinding
import com.app.movieapp.viewModel.MovieViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: movieAdapter
//    private lateinit var upcomingAdapter: upcomingAdapter


    companion object {
        const val image = "com.app.movieapp.activities.poster_path"
        const val id = "com.app.movieapp.activities.id"
        const val name = "com.app.movieapp.activities.title"
        const val release_date = "com.app.movieapp.activities.release_date"
        const val langyuage = "com.app.movieapp.activities.original_language"
        const val overview = "com.app.movieapp.activities.overview"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        prepareRecyclerView()
        viewModel.getTrendingMovies()
        observeMovies()



//        prepareUpcomigRecyclerView()
//        viewModel.getUpcomigMovies()
//        observeUpcomingMovies()






        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg")
            .into(binding.random)

        onMovieItemCLick()
        binding.favorite.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)


        }

    }

//    private fun observeUpcomingMovies() {
//        viewModel.observeUpcomingMOvieLIveData().observe(this, Observer { upcomingList ->
//            upcomingAdapter.setUpcomingLIst(upcomingList)
//            Log.d("test", movieAdapter.toString())
//
//        })
//    }

    private fun observeMovies() {
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            movieAdapter.setMovieList(movieList)

        })
    }


    private fun onMovieItemCLick() {
        movieAdapter.onItemClick = { result ->
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra(image, result.poster_path)
            intent.putExtra(id, result.id)
            intent.putExtra(name, result.title)
            intent.putExtra(release_date, result.release_date)
            intent.putExtra(langyuage, result.original_language)
            intent.putExtra(overview, result.overview)
            startActivity(intent)
        }
    }

//    private fun prepareUpcomigRecyclerView() {
//        upcomingAdapter = upcomingAdapter()
//        binding.rcvUpcoming.apply {
//            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
//            adapter = upcomingAdapter
//        }
//    }

    private fun prepareRecyclerView() {
        movieAdapter = movieAdapter()
        binding.rcvTrending.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter


        }
    }
}