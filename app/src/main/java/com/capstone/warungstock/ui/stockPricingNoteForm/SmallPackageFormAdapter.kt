package com.capstone.warungstock.ui.stockPricingNoteForm

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.warungstock.data.local.realm.model.SmallPackage
import com.capstone.warungstock.databinding.ItemSmallPackageForFormBinding

class SmallPackageFormAdapter : ListAdapter<SmallPackage,SmallPackageFormAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(val binding : ItemSmallPackageForFormBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SmallPackage) {
            with(binding){
                textInputName.setText(item.packagename.toString())
                textInputPrice.setText(item.packageprice.toString())
                textInputName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(text: Editable?) {
                        item.packagename = text.toString()
                    }
                })
                textInputPrice.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(text: Editable?) {
                        if (text.isNullOrBlank()){
                            item.packageprice = 0
                        }else{
                            item.packageprice = text.toString().toInt()
                        }
                    }
                })
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSmallPackageForFormBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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