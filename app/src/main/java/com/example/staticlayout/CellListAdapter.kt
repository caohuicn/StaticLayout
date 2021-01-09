package com.example.staticlayout

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.staticlayout.ui.ObjectCell

/**
 *
 * @author Hui Cao
 */
abstract class CellListAdapter: ListAdapter<CharSequence, CellListAdapter.CellViewHolder>(CellDiffCallback()) {
    class CellViewHolder(view: ObjectCell): RecyclerView.ViewHolder(view) {
        val cell = view
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.cell.setText(getItem(position))
    }
}

class CellDiffCallback: DiffUtil.ItemCallback<CharSequence>() {
    override fun areItemsTheSame(oldItem: CharSequence, newItem: CharSequence): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CharSequence, newItem: CharSequence): Boolean {
        return oldItem.toString() == newItem.toString()
    }

}