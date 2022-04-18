package com.mohit.bootesnull.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohit.bootesnull.adapters.EventListAdapter
import com.mohit.bootesnull.databinding.FragmentEventBinding
import com.mohit.bootesnull.models.AllEventsDatum
import com.mohit.bootesnull.util.Common
import com.mohit.bootesnull.viewmodels.EventViewModel

class EventFragment : Fragment() {
    private var viewModel: EventViewModel? = null
    private lateinit var binding: FragmentEventBinding
    private var eventListAdapter: EventListAdapter? = null
    private var list: List<AllEventsDatum>? = null
    private var layoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        eventListAdapter = EventListAdapter(this)
        viewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        viewModel!!.init()

        call()
    }

    private fun call() {
        viewModel!!.mutableLiveData!!.observe(this) { volumesResponse ->
            if (volumesResponse != null) {
                Common.dismissLoadingProgress()

                list = volumesResponse.data// list created to save in local database
//                for (item in list!!) {
//                    AppDatabase.getInstance(requireActivity())
//                        .dao()
//                        .addAlbums(item)
//                }
//                SharePreference.getInstance(requireContext()).setBooleanPref(
//                    SharePreference.check, true
//                ).toString()

                eventListAdapter!!.setResults(list!!)

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        eventAdapter()

//Local database check
//        val isLocal =
//            SharePreference.getInstance(requireActivity()).getBooleanPref(SharePreference.check)
//        if (isLocal) {
//            val list = AppDatabase.getInstance(requireActivity()).dao().getAlbums()
//            adapter!!.setResults(list)
//
//         } else {
//             AppDatabase.getInstance(requireActivity())
//                .dao()
//                .deleteAlbum()
//        }
        performSearch()
        return binding.root

    }




    //set event adapter
    private fun eventAdapter() {
        layoutManager = LinearLayoutManager(requireActivity())
        binding.rvEvents.layoutManager = layoutManager
        binding.rvEvents.adapter = eventListAdapter
        binding.rvEvents.itemAnimator = DefaultItemAnimator()
    }

    //mutable search
    private fun performSearch() {
        Common.showLoadingProgress(requireActivity())
        viewModel!!.eventListApi()
    }


}