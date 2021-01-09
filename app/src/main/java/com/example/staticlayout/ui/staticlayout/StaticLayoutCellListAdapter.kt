package com.example.staticlayout.ui.staticlayout

import android.view.ViewGroup
import com.example.staticlayout.CellListAdapter
import com.example.staticlayout.ui.textview.TextViewCell

/**
 *
 * @author Hui Cao
 */
class StaticLayoutCellListAdapter: CellListAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        return CellViewHolder(StaticLayoutCell(parent.context))
    }
}