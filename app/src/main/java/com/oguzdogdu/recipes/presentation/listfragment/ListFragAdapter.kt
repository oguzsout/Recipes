package com.oguzdogdu.recipes.presentation.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.oguzdogdu.recipes.databinding.ListRowBinding
import com.oguzdogdu.recipes.domain.model.Recipe
import com.oguzdogdu.recipes.util.convertToExpression

class ListFragAdapter :
    RecyclerView.Adapter<ListFragAdapter.RecipeHolder>() {
    inner class RecipeHolder(private val binding: ListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {
                imgRecipe.load(recipe.image) {
                    transformations(RoundedCornersTransformation(25f))
                }
                tvRecipeTitle.text = recipe.title
                "Vegan: ${recipe.vegan.convertToExpression()}".also { tvVegan.text = it }
                "Vegetarian: ${recipe.vegetarian.convertToExpression()}".also {
                    tvVegetarian.text = it
                }
                "Health: ${recipe.veryHealthy.convertToExpression()}".also { tvHealth.text = it }
            }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        return RecipeHolder(
            ListRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
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
