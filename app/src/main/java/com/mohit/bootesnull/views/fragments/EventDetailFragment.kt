package com.mohit.bootesnull.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
 import com.bumptech.glide.Glide
import com.mohit.bootesnull.R
import com.mohit.bootesnull.databinding.FragmentEventDetailBinding
import com.mohit.bootesnull.models.Data
import com.mohit.bootesnull.util.Common
import com.mohit.bootesnull.util.Common.getDate
import com.mohit.bootesnull.util.Common.getDateTime
import com.mohit.bootesnull.util.Common.getDay
import com.mohit.bootesnull.util.Common.getTime
import com.mohit.bootesnull.viewmodels.EventDetailViewModel


class EventDetailFragment : Fragment() {
    private var viewModel: EventDetailViewModel? = null
    lateinit var binding: FragmentEventDetailBinding
    var list: Data? = null
     var event_id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(EventDetailViewModel::class.java)
        viewModel!!.init()
        event_id = requireArguments().getString("event_id").toString()
        call()
    }


    private fun call() {
        viewModel!!.mutableLiveData!!.observe(this) { volumesResponse ->
            if (volumesResponse != null) {
                Common.dismissLoadingProgress()

                list = volumesResponse.data!!// this list is created to use as local database .
                setDataViews(volumesResponse.data!!)

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataViews(data: Data) {
        binding.eventTitle.text = data.title.toString()
        binding.tvGoing.text = data.total_going.toString()
        binding.tvInterested.text = data.total_interested.toString()
        Glide.with(requireActivity())
            .load(Common.BaseImageUrl + data.cover_image.toString())
            .placeholder(R.drawable.demo_event)
            .into(binding.eventImag)
        val startD = getDateTime(data.start_date.toString())
        val endD = getDateTime(data.end_date.toString())
        val mDay = getDay(data.start_date.toString())
        val mDate = getDate(data.start_date.toString())
        val mTime = getTime(data.start_date.toString())
        binding.tvEventDate.text = "$startD to $endD"
        binding.tvLocation.text = data.location.toString()
        binding.tvLocation.text = data.location.toString()
        binding.tvHostedBy.text = data.user_id?.full_name.toString()
        binding.tvDay.text = mDay
        binding.tvDate.text = mDate
        binding.tvTime.text = mTime


        Glide.with(requireActivity())
            .load(Common.BaseImageUrl + data.user_id?.profile_image)
            .into(binding.ivUser)

        val bio = data.about.toString()
        if (bio.isNotEmpty()) {
            binding.rlBio.visibility = View.VISIBLE
            binding.txtBio.text = bio
            binding.txtBioMore.text = bio


            //for line count
            binding.txtBio.post {
                val l: Layout = binding.txtBio.layout
                val lines: Int = l.lineCount
                if (lines > 0) if (l.getEllipsisCount(lines - 1) > 0) {
                    binding.tvViewMore.visibility = View.VISIBLE
                }
            }
        }
        when (data.privacy) {
            1 -> {
                binding.privacy.text = "Public"
                binding.privacyImg.setImageResource(R.drawable.globe)

            }
            2 -> {
                binding.privacy.text = "Private"
                binding.privacyImg.setImageResource(R.drawable.members)
            }
            else -> {
                binding.privacy.text = "Private"
                binding.privacyImg.setImageResource(R.drawable.lock_icon)
            }
        }
        binding.tvViewMore.setOnClickListener {
            if (binding.tvViewMore.text == resources.getString(R.string.read_more)) {
                binding.txtBio.visibility = View.GONE
                binding.txtBioMore.visibility = View.VISIBLE
                binding.tvViewMore.text = resources.getString(R.string.read_less)
            } else {
                binding.txtBio.visibility = View.VISIBLE
                binding.txtBioMore.visibility = View.GONE
                binding.tvViewMore.text = resources.getString(R.string.read_more)
            }

        }


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailBinding.inflate(layoutInflater, container, false)
        performSearch()
        return binding.root

    }


    //mutable search
    private fun performSearch() {
        Common.showLoadingProgress(requireActivity())
        viewModel!!.eventListApi(event_id)
    }


}