package com.jbkalit.presentation.features.datafeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jbkalit.presentation.databinding.FragmentDatafeedBinding

class DatafeedFragment : Fragment() {

    private var _binding: FragmentDatafeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDatafeedBinding.inflate(inflater, container, false)
        return binding.root
    }

}
