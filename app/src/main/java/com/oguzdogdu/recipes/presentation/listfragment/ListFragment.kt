package com.oguzdogdu.recipes.presentation.listfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.recipes.R
import com.oguzdogdu.recipes.databinding.FragmentListBinding
import com.oguzdogdu.recipes.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private var binding: FragmentListBinding? = null
    private val viewModel: ListViewModel by viewModels()
    private val mAdapter = ListFragAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        setupRv()
        observeData()
        sendData()
    }
    private fun setupRv() {
        binding?.rvMain?.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
    private fun observeData() {
        viewModel.recipeResponse.observe(viewLifecycleOwner, { recipes ->
            when (recipes.status) {
                Status.SUCCESS -> {
                    recipes.data.let { recipeResponse ->
                        if (recipeResponse != null) {
                            mAdapter.recipes = recipeResponse.recipes
                            binding?.shimmer?.stopShimmer()
                            binding?.shimmer?.visibility = View.GONE
                        }
                    }
                }
                Status.ERROR -> {

                    recipes.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")
                        binding?.shimmer?.startShimmer()
                    }
                }
                Status.LOADING -> {
                    binding?.shimmer?.startShimmer()
                }
            }
        })
    }
    private fun sendData() {
        mAdapter.setOnItemClickListener {

            findNavController().navigate(
                R.id.action_listFragment_to_detailFragment
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}