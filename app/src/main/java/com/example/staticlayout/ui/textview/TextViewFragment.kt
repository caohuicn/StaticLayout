package com.example.staticlayout.ui.textview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.staticlayout.R
import com.example.staticlayout.TextViewModel

class TextViewFragment : Fragment() {

    private lateinit var viewModel: TextViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProvider(requireActivity()).get(TextViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_textview, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TextViewCellListAdapter()
        adapter.submitList(viewModel.list.value)
        recyclerView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager

        return root
    }
}