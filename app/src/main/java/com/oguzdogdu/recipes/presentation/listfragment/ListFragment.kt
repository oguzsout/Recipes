package com.oguzdogdu.recipes.presentation.listfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzdogdu.recipes.R
import com.oguzdogdu.recipes.databinding.FragmentListBinding
import com.oguzdogdu.recipes.presentation.base.BaseFragment
import com.oguzdogdu.recipes.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private val viewModel: ListViewModel by viewModels()
    private val mAdapter = ListFragAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        observeData()
        sendData()
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
                    recipes.data.let { recipeResponse ->
                        if (recipeResponse != null) {
                            mAdapter.recipes = recipeResponse.recipes
                            binding.shimmer.stopShimmer()
                            binding.shimmer.visibility = View.GONE
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
    private fun sendData() {
        mAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("recipeArgs", it)
            }
            findNavController().navigate(
                R.id.action_listFragment_to_detailFragment,
                bundle
            )
        }
    }
}