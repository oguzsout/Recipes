package com.oguzdogdu.recipes.presentation.detailfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.oguzdogdu.recipes.R
import com.oguzdogdu.recipes.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
    }
}