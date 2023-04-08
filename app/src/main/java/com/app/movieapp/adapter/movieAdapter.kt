package com.app.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.databinding.TrendingListBinding
import com.app.movieapp.prob.Result
import com.bumptech.glide.Glide

class movieAdapter : RecyclerView.Adapter<movieAdapter.ViewHolder>() {

    private var movieList = ArrayList<Result>()
    lateinit var onItemClick: ((Result)->Unit)

    fun setMovieList(movieList:List<Result>){
        this.movieList = movieList as ArrayList<Result>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: TrendingListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TrendingListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
            .into(holder.binding.trendingImage)
//        holder.binding.textView.text = movieList[position].title

        holder.itemView.setOnClickListener {
            onItemClick.invoke(movieList[position])
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}