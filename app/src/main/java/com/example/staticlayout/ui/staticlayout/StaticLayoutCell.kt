package com.example.staticlayout.ui.staticlayout

import android.content.Context
import android.os.Trace
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import com.example.staticlayout.ui.ObjectCell

class StaticLayoutCell @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ObjectCell(context, attrs, defStyleAttr) {
    private var text: CharSequence = ""
    private val textView =  StaticLayoutView(context)
    private var textWidth = 0
    private var paint = TextPaint()

    init {
        paint.textSize = textSize
        addView(textView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
    }
    override fun setText(text: CharSequence) {
        this.text = text
        configureStaticLayout()
        requestLayout()
        Log.d("StaticLayoutCell", "setText: ${text.substring(0, text.indexOf(":"))}")
    }

    private fun configureStaticLayout() {
        var builder = StaticLayout.Builder.obtain(text, 0, text.length, paint, textWidth).setMaxLines(10)
        textView.staticLayout = builder.build()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Trace.beginSection("SLM")
        var newTextWidth = MeasureSpec.getSize(widthMeasureSpec)//assume EXACT
        if (newTextWidth != textWidth) {
            //if width keeps changing, using static layout won't be able to improve performance
            textWidth = newTextWidth
            configureStaticLayout()
        }
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
