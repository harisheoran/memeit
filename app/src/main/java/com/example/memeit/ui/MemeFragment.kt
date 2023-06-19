package com.example.memeit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.memeit.MainViewModel
import com.example.memeit.databinding.FragmentMemeBinding


class MemeFragment : Fragment() {
    private var _binding: FragmentMemeBinding? = null
    private val binding get() = _binding!!

    private val myViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMemeBinding.inflate(inflater)
        binding.viewmodel = myViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}