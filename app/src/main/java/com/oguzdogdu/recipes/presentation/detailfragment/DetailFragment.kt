package com.oguzdogdu.recipes.presentation.detailfragment

import android.os.Bundle
import android.view.View
import androidx.core.text.parseAsHtml
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.oguzdogdu.recipes.base.BaseFragment
import com.oguzdogdu.recipes.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        val recipeList = args.recipeArgs
        binding.tvTitle.text = recipeList?.title
        binding.tvSummary.text = recipeList?.summary?.parseAsHtml()
        binding.tvCredit.text = recipeList?.creditsText
        binding.tvInstruction.text = recipeList?.instructions?.parseAsHtml()
        binding.tvUrl.text = recipeList?.spoonacularSourceUrl
        binding.imageListItem.load(recipeList?.image){
            transformations(RoundedCornersTransformation(25f))
        }
    }
}