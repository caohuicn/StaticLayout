package com.example.staticlayout.ui

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.example.staticlayout.R

/**
 *
 * @author Hui Cao
 */
abstract class ObjectCell @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    protected val textSize = resources.getDimension(R.dimen.text_size)
    protected val textPadding = resources.getDimension(R.dimen.text_padding).toInt()

    abstract fun setText(text: CharSequence)
}