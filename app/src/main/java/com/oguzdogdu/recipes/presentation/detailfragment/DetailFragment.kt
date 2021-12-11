package com.oguzdogdu.recipes.presentation.detailfragment

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.text.parseAsHtml
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.oguzdogdu.recipes.databinding.FragmentDetailBinding
import com.oguzdogdu.recipes.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        backStack()
    }

    private fun setData() {
        val recipeList = args.recipeArgs
        binding.tvTitle.text = recipeList?.title
        binding.tvSummary.text = recipeList?.summary?.parseAsHtml()
        binding.tvUrl.text = recipeList?.spoonacularSourceUrl?.parseAsHtml()
        binding.imageListItem.load(recipeList?.image) {
            crossfade(true)
            crossfade(500)
            transformations(RoundedCornersTransformation(25f))
        }
    }
    private fun backStack() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}