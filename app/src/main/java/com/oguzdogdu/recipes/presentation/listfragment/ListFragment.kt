package com.oguzdogdu.recipes.presentation.listfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.recipes.base.BaseFragment
import com.oguzdogdu.recipes.databinding.FragmentListBinding
import com.oguzdogdu.recipes.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()

    private var listAdapter = ListFragAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        transferringDataToDetailScreen()
        observeData()
    }

    private fun setupRv() {
        binding.rvMain.apply {
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
        viewModel.recipeResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideShimmerEffect()
                    it.data.let { recipeResponse ->
                        if (recipeResponse != null) {
                            listAdapter.recipies = recipeResponse.recipes
                        }
                    }
                }
                Status.ERROR -> {
                    hideShimmerEffect()
                    it.message?.let { message ->
                        Log.e("TAG", "An error occured: $message")

                    }
                }
                Status.LOADING -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun showShimmerEffect() {
        binding.shimmer.startShimmer()
        binding.shimmer.visibility = View.VISIBLE
        binding.rvMain.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmer.stopShimmer()
        binding.shimmer.visibility = View.GONE
        binding.rvMain.visibility = View.VISIBLE
    }
}