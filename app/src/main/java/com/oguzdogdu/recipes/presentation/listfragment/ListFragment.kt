package com.oguzdogdu.recipes.presentation.listfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.recipes.databinding.FragmentListBinding
import com.oguzdogdu.recipes.presentation.base.BaseFragment
import com.oguzdogdu.recipes.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()

    private var listAdapter = ListFragAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        observeData()
    }

    private fun setupRv() {
        binding.rvMain.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun observeData() {
        viewModel.recipeResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data.let { recipeResponse ->
                        if (recipeResponse != null) {
                            listAdapter.recipies = recipeResponse.recipes
                            binding.shimmer.stopShimmer()
                            binding.shimmer.visibility = View.GONE
                        }
                    }
                }
                Status.ERROR -> {
                    it.message?.let { message ->
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
}