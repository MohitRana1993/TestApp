package com.mohit.bootesnull.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohit.bootesnull.R
import com.mohit.bootesnull.models.AllEventsDatum
import com.mohit.bootesnull.util.Common
import com.mohit.bootesnull.views.fragments.EventFragment


class EventListAdapter(private var context: EventFragment) :
    RecyclerView.Adapter<EventListAdapter.BookSearchResultHolder>() {
    private var data: List<AllEventsDatum> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchResultHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_event, parent, false)
        return BookSearchResultHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BookSearchResultHolder, position: Int) {
        holder.tvTitle.text = data[position % data.size].title.toString()
        holder.tvLocation.text = data[position].location
        holder.tvInterestedCount.text =
            data[position].total_interested.toString() + " " + context.resources.getString(R.string.interested)
        if (data[position].is_interested == 1) {
            holder.llIsInterested.visibility = View.VISIBLE
        } else {
            holder.llIsInterested.visibility = View.GONE
        }
        //  eventClick.eventClick(data!![position]._id, "join")
        Glide.with(context).load(Common.BaseImageUrl + data[position].cover_image.toString())
            .into(holder.ivEvent)



        holder.itemView.setOnClickListener {
// as per defined in your FragmentContainerView
            val bundle = Bundle()
            bundle.putString("event_id", data[position]._id.toString())
            val navHostFragment =
                context.requireActivity().supportFragmentManager.findFragmentById(R.id.activity_main_navhostfragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_eventsFragment_to_eventDetailFragment, bundle)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setResults(volumesResponse: List<AllEventsDatum>) {
        this.data = volumesResponse
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class BookSearchResultHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.tvEventTitle)
        var ivEvent: ImageView = view.findViewById(R.id.ivEvent)
        var tvLocation: TextView = view.findViewById(R.id.tvLocation)
        var tvInterestedCount: TextView = view.findViewById(R.id.tvInterestedCount)
        var llIsInterested: LinearLayout = view.findViewById(R.id.ll_is_interested)
    }
}