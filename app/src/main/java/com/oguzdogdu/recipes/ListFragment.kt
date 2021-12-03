package com.oguzdogdu.recipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.oguzdogdu.recipes.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
    }
}