package com.catganisation.catalog.presentation.cats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catganisation.catalog.databinding.CatBreedItemBinding

class CatBreedsAdapter : RecyclerView.Adapter<CatBreedsAdapter.CatBreedVH>() {

    private var catBreeds = listOf<CatBreedViewData>()

    fun updateList(catBreeds: List<CatBreedViewData>) {
        this.catBreeds = catBreeds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CatBreedItemBinding.inflate(inflater, parent, false)
        return CatBreedVH(binding)
    }

    override fun getItemCount(): Int = catBreeds.size

    override fun onBindViewHolder(holder: CatBreedVH, position: Int) {
        val catBreed = catBreeds[position]
        holder.binding.viewData = catBreed
    }

    inner class CatBreedVH(
        val binding: CatBreedItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}