package com.capstone.warungstock.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.warungstock.data.local.realm.model.ItemStock
import com.capstone.warungstock.databinding.ItemStockPricingNoteBinding

class StockPricingNoteAdapter : ListAdapter<ItemStock, StockPricingNoteAdapter.ViewHolder>(
    Diff_Callback
) {

    private var newListener : AddNewListener? = null

    interface AddNewListener{
        fun setSmallPackageNestedRecylerView( item : ItemStock,binding: ItemStockPricingNoteBinding )
        fun setOnCLickListener(item : ItemStock,binding: ItemStockPricingNoteBinding)
    }

    fun setNewListener(newListener : AddNewListener){
        this.newListener = newListener
    }

    inner class ViewHolder(val binding : ItemStockPricingNoteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemStock) {
            with(binding){
                textViewItemStockPricingName.text = item.itemname
                cardViewItemStockPricingNote.setOnClickListener {
                    newListener?.setOnCLickListener(item,binding)
                }
            }
            if (item.smalPackages != null){
                Log.i("datas search adapter",item.itemname.toString())
                newListener?.setSmallPackageNestedRecylerView(item,binding)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemStockPricingNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val Diff_Callback = object : DiffUtil.ItemCallback<ItemStock>(){
            override fun areItemsTheSame(oldItem: ItemStock, newItem: ItemStock): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: ItemStock, newItem: ItemStock): Boolean {
                return oldItem._id == newItem._id
            }

        }
    }
}