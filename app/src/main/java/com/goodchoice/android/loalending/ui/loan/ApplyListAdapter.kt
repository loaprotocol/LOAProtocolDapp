package com.goodchoice.android.loalending.ui.loan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodchoice.android.loalending.R
import com.goodchoice.android.loalending.databinding.ItemApplyListBinding
import com.goodchoice.android.loalending.model.Apply
import com.goodchoice.android.loalending.util.constant.Code
import timber.log.Timber

class ApplyListAdapter(private val fragment: Fragment) :
    ListAdapter<Apply, ApplyListAdapter.ApplyListViewHolder>(ApplyListDiffUtil) {

    inner class ApplyListViewHolder(private val binding: ItemApplyListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Apply) {
//            Timber.e(item.toString())
            binding.apply = item
            when (item.status) {
                // 신청완료
                Code.APPLY_STATUS_COMPLETE
                    // 상태 배경색 변경
                -> {
                    binding.itemApplyListStatusKor.setBackgroundColor(
                        ContextCompat.getColor(binding.root.context, R.color.colorMint)
                    )
                    binding.itemApplyListCancel.visibility = View.VISIBLE

                }
                // 검토중
                Code.APPLY_STATUS_ING
                -> {
                    binding.itemApplyListStatusKor.setBackgroundColor(
                        ContextCompat.getColor(binding.root.context, R.color.color7Grey)
                    )
                    binding.itemApplyListCancel.visibility = View.GONE
                }
                // 대출 진행 완료
                Code.APPLY_STATUS_OK
                -> {
                    binding.itemApplyListStatusKor.setBackgroundColor(
                        ContextCompat.getColor(binding.root.context, R.color.colorMint)
                    )
                    binding.itemApplyListCancel.visibility = View.GONE
                    binding.itemApplyListDetail2.visibility = View.VISIBLE
                }
                // 대출 불가
                Code.APPLY_STATUS_NO
                -> {
                    binding.itemApplyListStatusKor.setBackgroundColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.colorTextBlack
                        )
                    )
                    binding.itemApplyListCancel.visibility = View.GONE
                }
                // 신청취소
                Code.APPLY_STATUS_CANCEL
                -> {
                    binding.itemApplyListStatusKor.setBackgroundColor(
                        ContextCompat.getColor(binding.root.context, R.color.colorRed)
                    )
                    binding.itemApplyListCancel.visibility = View.GONE
                }
            }
            // 상세보기 클릭
            binding.itemApplyListDetail.setOnClickListener {
                val dialog = ApplyListDetailDialog(item)
                val fragmentTransaction = fragment.childFragmentManager.beginTransaction()
                dialog.show(fragmentTransaction, "")
            }

            //신청취소 클릭
            binding.itemApplyListCancel.setOnClickListener {
                val dialog = ApplyCancelDialog(item)
                val fragmentTransaction = fragment.childFragmentManager.beginTransaction()
                dialog.show(fragmentTransaction, "")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<ItemApplyListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_apply_list,
            parent,
            false
        ).let {
            ApplyListViewHolder(it)
        }

    override fun onBindViewHolder(holder: ApplyListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


object ApplyListDiffUtil : DiffUtil.ItemCallback<Apply>() {
    override fun areItemsTheSame(oldItem: Apply, newItem: Apply): Boolean {
        return oldItem.seq == newItem.seq

    }

    override fun areContentsTheSame(oldItem: Apply, newItem: Apply): Boolean {
        return oldItem == newItem
    }

}