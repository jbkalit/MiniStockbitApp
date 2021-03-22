package com.jbkalit.presentation.features.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.presentation.databinding.FragmentWatchlistBinding
import com.jbkalit.presentation.event.EventObserver
import com.jbkalit.presentation.extension.*
import com.jbkalit.presentation.features.watchlist.adapter.WatchlistAdapter
import com.jbkalit.presentation.features.watchlist.viewmodel.WatchlistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class WatchlistFragment : Fragment() {

    private val watchlistViewModel by viewModel<WatchlistViewModel>()

    private lateinit var binding: FragmentWatchlistBinding
    private lateinit var adapter: WatchlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setUpListener()
        setupObserver()
        watchlistViewModel.fetchCryptos(0)
    }

    private fun setUpListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.progressBar.setGone()
            adapter.submitList(null)
            watchlistViewModel.fetchCryptos(0)
        }
        binding.watchlistRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    watchlistViewModel.fetchCryptos(1)
                    binding.progressBar.setGone()
                    binding.loadMoreProgressBar.setVisible()
                    binding.loadMoreProgressBar.showSlideUp()
                }
            }
        })
    }

    private fun setUpView() {
        with(binding) {
            watchlistRecyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = WatchlistAdapter(mutableListOf()) {}
            watchlistRecyclerView.adapter = adapter
        }
    }

    private fun setupObserver() {
        watchlistViewModel.crypto.observe(viewLifecycleOwner, Observer { response ->
            response?.let {
                if (it.isEmpty()) {
                    binding.noResults.visibility = View.VISIBLE
                } else {
                    binding.noResults.visibility = View.GONE
                    adapter.addCryptoToList(it.toMutableList())
                    adapter.notifyDataSetChanged()
                }
            }
        })

        watchlistViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.setVisible()
            } else {
                binding.progressBar.setGone()
                binding.swipeRefreshLayout.isRefreshing = false
                binding.loadMoreProgressBar.setGone()
            }
        })

        watchlistViewModel.error.observe(viewLifecycleOwner, EventObserver {
            view?.showSnackbar(it)
        })
    }

}