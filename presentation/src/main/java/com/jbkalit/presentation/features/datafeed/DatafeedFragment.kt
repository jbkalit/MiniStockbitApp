package com.jbkalit.presentation.features.datafeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbkalit.presentation.databinding.FragmentDatafeedBinding
import com.jbkalit.presentation.features.datafeed.adapter.DatafeedAdapter
import com.jbkalit.presentation.features.datafeed.viewmodel.DatafeedViewModel
import com.jbkalit.presentation.util.Constants.FIRST_CRYPTO
import com.jbkalit.presentation.util.Constants.SECOND_CRYPTO
import com.jbkalit.presentation.util.Constants.THIRD_CRYPTO
import com.jbkalit.presentation.util.NumberUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class DatafeedFragment : Fragment() {

    private val viewModel by viewModel<DatafeedViewModel>()

    private lateinit var binding: FragmentDatafeedBinding
    private lateinit var adapter: DatafeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatafeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpObserver()
    }

    private fun setUpView() {
        with(binding) {
            dataFeedReyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = DatafeedAdapter(mutableListOf())
            dataFeedReyclerView.adapter = adapter
        }
    }

    private fun setUpObserver() {
        viewModel.webSocketData.observe(viewLifecycleOwner, Observer { response ->
            when (response.symbol) {

                FIRST_CRYPTO -> {
                    binding.firstCodeTextView.text = FIRST_CRYPTO
                    binding.firstPriceTextView.text =
                        response.topTierFullVolume?.let { NumberUtils.formatPrice(it) }
                }
                SECOND_CRYPTO -> {
                    binding.secondCodeTextView.text = SECOND_CRYPTO
                    binding.secondPriceTextView.text =
                        response.topTierFullVolume?.let { NumberUtils.formatPrice(it) }
                }
                THIRD_CRYPTO -> {
                    binding.thirdCodeTextView.text = THIRD_CRYPTO
                    binding.thirdPriceTextView.text =
                        response.topTierFullVolume?.let { NumberUtils.formatPrice(it) }
                }
            }
        })
    }

}
