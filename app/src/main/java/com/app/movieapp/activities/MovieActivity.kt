package com.app.movieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.app.movieapp.R
import com.app.movieapp.databinding.ActivityMovieBinding
import com.app.movieapp.viewModel.MovieViewModel
import com.bumptech.glide.Glide
import kotlin.properties.Delegates

class MovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieBinding
    lateinit var Image:String
    lateinit var Name:String
    lateinit var language:String
    lateinit var date:String
    lateinit var overview:String
    lateinit var viewModel: MovieViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        getinformationFromIntent()
        setInformation()
    }

    private fun setInformation() {
        Glide.with(applicationContext)
            .load("https://image.tmdb.org/t/p/w500"+Image)
            .into(binding.movieImg)
        binding.movieName.text = Name
        binding.date.text = "Release Date - ${date}"
        binding.language.text = "Language - ${language}"
        binding.overview.text = overview

    }

    private fun getinformationFromIntent() {
        val intent = intent
        Name = intent.getStringExtra(MainActivity.name)!!
        overview = intent.getStringExtra(MainActivity.overview)!!
        Image = intent.getStringExtra(MainActivity.image)!!
        language = intent.getStringExtra(MainActivity.langyuage)!!
        date = intent.getStringExtra(MainActivity.release_date)!!

    }
}