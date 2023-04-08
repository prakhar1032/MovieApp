package com.app.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.movieapp.databinding.UpcomingListBinding
import com.app.movieapp.prob.ResultX
import com.bumptech.glide.Glide

class upcomingAdapter : RecyclerView.Adapter<upcomingAdapter.ViewHolder>() {

    private var upcomingList = ArrayList<ResultX>()
//    lateinit var onItemClickUpc:((ResultX)->Unit)

    fun setUpcomingLIst(upcomingList:List<ResultX>){
        this.upcomingList = upcomingList as ArrayList<ResultX>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: UpcomingListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(UpcomingListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+upcomingList[position].poster_path)
            .into(holder.binding.upcomingImage)



//        holder.binding.uptext.text = upcomingList[position].title

//        holder.itemView.setOnClickListener {
//            onItemClickUpc.invoke(upcomingList[position])
//        }
    }

    override fun getItemCount(): Int {
        return  upcomingList.size
    }
}