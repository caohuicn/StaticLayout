package com.example.staticlayout.ui.textview

import android.content.Context
import android.os.Trace
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue.COMPLEX_UNIT_PX
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.example.staticlayout.ui.ObjectCell

/**
 *
 * @author Hui Cao
 */
class TextViewCell @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ObjectCell(context, attrs, defStyleAttr) {
    private val textView =  TextView(context)
    init {
        textView.maxLines = 10
        textView.setTextSize(COMPLEX_UNIT_PX, textSize)
        addView(textView, LayoutParams(MATCH_PARENT, WRAP_CONTENT))
    }
    override fun setText(text: CharSequence) {
        textView.text = text
        Log.d("TextViewCell", "setText: ${text.substring(0, text.indexOf(":"))}")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Trace.beginSection("TVM")
        textView.measure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            getDefaultSize(textView.measuredWidth + textPadding * 2, widthMeasureSpec),
            getDefaultSize(textView.measuredHeight + textPadding * 2, heightMeasureSpec)
        )

        Trace.endSection()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        textView.layout(textPadding, textPadding, r - l, b - t)
    }
}