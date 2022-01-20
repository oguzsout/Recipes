package com.oguzdogdu.recipes.presentation.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oguzdogdu.recipes.R
import com.oguzdogdu.recipes.databinding.SmallRecipeListRowBinding
import com.oguzdogdu.recipes.domain.model.Recipe
import com.oguzdogdu.recipes.util.loadImage

class SmallRecipeAdapter : RecyclerView.Adapter<SmallRecipeAdapter.SmallRecipeViewHolder>() {

    inner class SmallRecipeViewHolder(private val binding: SmallRecipeListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {
                imageViewRecipies.loadImage(recipe.image)
                textViewTitle.text = recipe.title
                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(recipe)
                    }
                }
            }
        }

        private var onItemClickListener: ((Recipe) -> Unit)? = null

        fun setOnItemClickListener(listener: (Recipe) -> Unit) {
            onItemClickListener = listener
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.small_recipe_list_row
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallRecipeViewHolder {
        return SmallRecipeViewHolder(
            SmallRecipeListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SmallRecipeViewHolder, position: Int) {
        val recipeItem = recipies[position]
        holder.bind(recipeItem)
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var recipies: List<Recipe>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun getItemCount() = recipies.size
}