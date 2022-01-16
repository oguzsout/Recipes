package com.oguzdogdu.recipes.presentation.listfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.recipes.base.BaseFragment
import com.oguzdogdu.recipes.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()

    private var listAdapter = ListFragAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        transferringDataToDetailScreen()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rvListfrag.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun transferringDataToDetailScreen() {
        listAdapter.setOnItemClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun observeData() {
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.recipeList.collect { recipeList ->
                if (recipeList.isLoading) {
                    showShimmerEffect()
                }
                if (recipeList.error.isNotBlank()) {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(), recipeList.error, Toast.LENGTH_SHORT).show()
                }

                recipeList.data?.let {

                    if (it.isEmpty()) {
                        showShimmerEffect()
                    }
                    hideShimmerEffect()
                    listAdapter.recipies = it
                }
            }
        }
    }

    private fun showShimmerEffect() {
        binding.shimmer.startShimmer()
        binding.shimmer.visibility = View.VISIBLE
        binding.rvListfrag.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmer.stopShimmer()
        binding.shimmer.visibility = View.GONE
        binding.rvListfrag.visibility = View.VISIBLE
    }
}