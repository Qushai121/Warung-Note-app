package com.capstone.warungstock.ui.DetailStockPricingNote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import com.capstone.warungstock.databinding.ItemSmallPackageForDetailBinding

class SmallPackageNoteAdapter :ListAdapter<SmallPackage,SmallPackageNoteAdapter.ViewHolder>(
    DIFF_CALLBACK) {

    inner class ViewHolder(val binding : ItemSmallPackageForDetailBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(item: SmallPackage) {
            with(binding){
                textViewSmallPackageName.text = item.packagename
                textViewSmallPackagedPrice.text = item.packageprice.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSmallPackageForDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SmallPackage>() {
            override fun areItemsTheSame(oldItem: SmallPackage, newItem: SmallPackage): Boolean {
                return oldItem.packagename == newItem.packagename
            }

            override fun areContentsTheSame(oldItem: SmallPackage, newItem: SmallPackage): Boolean {
                return oldItem.packagename == newItem.packagename
            }

        }
    }
}