package com.goodchoice.android.loalending.ui.myAsset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.ItemMyassetTransactionKrwBinding
import com.goodchoice.android.loalending.model.Transaction

class SimpleTransactionKrwAdapter :
    ListAdapter<Transaction, SimpleTransactionKrwAdapter.SimpleTransactionKrwViewHolder>(
        SimpleTransactionKrwDiffUtil
    ) {


    inner class SimpleTransactionKrwViewHolder(private val binding: ItemMyassetTransactionKrwBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction) {
            binding.transaction = item
            if (item.transactionType == "출금") {
                binding.itemMyAssetTransactionKrwChangeAmount.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorRed
                    )
                )
            } else {
                binding.itemMyAssetTransactionKrwChangeAmount.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorBlue
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleTransactionKrwViewHolder = DataBindingUtil.inflate<ItemMyassetTransactionKrwBinding>(
        LayoutInflater.from(parent.context),
        R.layout.item_myasset_transaction_krw,
        parent,
        false
    ).let {
        SimpleTransactionKrwViewHolder(it)
    }

    override fun onBindViewHolder(holder: SimpleTransactionKrwViewHolder, position: Int) {
        if(position>1) return

        holder.bind(getItem(position))
    }

}

object SimpleTransactionKrwDiffUtil : DiffUtil.ItemCallback<Transaction>() {
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.seq == newItem.seq
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }

}