package com.example.staticlayout.ui.textview

import android.view.ViewGroup
import com.example.staticlayout.CellListAdapter

/**
 *
 * @author Hui Cao
 */
class TextViewCellListAdapter: CellListAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        return CellViewHolder(TextViewCell(parent.context))
    }
}