package com.oguzdogdu.recipes.presentation.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.oguzdogdu.recipes.R
import com.oguzdogdu.recipes.databinding.MainListRowBinding
import com.oguzdogdu.recipes.domain.model.Recipe
import com.oguzdogdu.recipes.util.loadImage

class RecipiesPhotoAdapter : RecyclerView.Adapter<RecipiesPhotoAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(private val binding: MainListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {
                imageViewRecipe.loadImage(recipe.image)
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
        return R.layout.main_list_row
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            MainListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
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