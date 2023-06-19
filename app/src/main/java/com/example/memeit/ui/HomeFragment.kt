package com.example.memeit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.memeit.MainViewModel
import com.example.memeit.MemeListAdapter
import com.example.memeit.R
import com.example.memeit.databinding.FragmentHomeBinding
import com.example.memeit.network.Meme
import com.example.memeit.utils.CustomClickListener

class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()

    // explicitly defining binding as null
    // as we know _binding will have a value after onCreateView()
    private var _binding: FragmentHomeBinding? = null

    // it shouldn't be null when you access it
    // get() means get only that once assigned, it cant be assigned to another
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.myViewModel = viewModel

        binding.homeRecylerView.adapter = MemeListAdapter(object : CustomClickListener {
            override fun onMemeClickListen(meme: Meme) {
                val img = meme.url
                viewModel.setMemeDetails(meme)
                findNavController().navigate(R.id.action_homeFragment_to_memeFragment)
            }
        }

        )
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        // as fragment no longer exist
        _binding = null
    }

}