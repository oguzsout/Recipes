package com.oguzdogdu.recipes.presentation.listfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.recipes.R
import com.oguzdogdu.recipes.databinding.FragmentListBinding
import com.oguzdogdu.recipes.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels()
    private val mAdapter = ListFragAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        observeData()
    }

    private fun setupRv() {
        binding.rvMain.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observeData() {
        viewModel.recipeResponse.observe(viewLifecycleOwner, { recipes ->
            when (recipes.status) {
                Status.SUCCESS -> {
                    binding.shimmer.stopShimmer()
                    recipes.data.let { recipeResponse ->
                        if (recipeResponse != null) {
                            mAdapter.recipes = recipeResponse.recipes
                        }
                    }
                }
                Status.ERROR -> {

                    recipes.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")
                        binding.shimmer.startShimmer()
                    }
                }
                Status.LOADING -> {
                    binding.shimmer.startShimmer()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}