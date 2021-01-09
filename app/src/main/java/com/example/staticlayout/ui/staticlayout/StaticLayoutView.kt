package com.example.staticlayout.ui.staticlayout

import android.content.Context
import android.graphics.Canvas
import android.text.StaticLayout
import android.util.AttributeSet
import android.view.View

/**
 *
 * @author Hui Cao
 */
class StaticLayoutView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var staticLayout: StaticLayout? = null
    set(value) {
        field = value
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (staticLayout == null ) {
            return super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
        setMeasuredDimension(staticLayout!!.width, staticLayout!!.height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (staticLayout == null) return
        canvas?.save()
        staticLayout?.draw(canvas)
        canvas?.restore()
    }
}