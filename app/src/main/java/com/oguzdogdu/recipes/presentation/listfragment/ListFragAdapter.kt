package com.oguzdogdu.recipes.presentation.listfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.oguzdogdu.recipes.databinding.ListRowBinding
import com.oguzdogdu.recipes.domain.model.Recipe
import com.oguzdogdu.recipes.util.setOf

class ListFragAdapter :
    ListAdapter<Recipe, ListFragAdapter.RecipeHolder>(RecipeComparator()) {
    inner class RecipeHolder(val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {
                imgRecipe.load(recipe.image) {
                    transformations(RoundedCornersTransformation(25f))
                }
                tvRecipeTitle.text = recipe.title
                "Vegan: ${recipe.vegan.setOf()}".also { tvVegan.text = it }
                "Vegetarian: ${recipe.vegetarian.setOf()}".also { tvVegetarian.text = it }
                "Health: ${recipe.veryHealthy.setOf()}".also { textViewHealth.text = it }
            }
        }
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
        val recipeItem = getItem(position)
        holder.bind(recipeItem)
        holder.binding.root.setOnClickListener { recipeView ->
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(recipeItem)
            findNavController(recipeView).navigate(action)
        }
    }
    class RecipeComparator : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem == newItem
    }
}