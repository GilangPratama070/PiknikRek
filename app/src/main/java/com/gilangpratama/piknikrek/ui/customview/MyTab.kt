package com.gilangpratama.piknikrek.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.gilangpratama.piknikrek.R
import com.google.android.material.tabs.TabLayout

class MyTab: TabLayout {

    private var first: Drawable? = null
    private var sec: Drawable? = null
    private var third: Drawable? = null
    private var fourth: Drawable? = null

    constructor(context: Context): super(context) { init() }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) { init() }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) { init() }

    private fun init() {
        first = ContextCompat.getDrawable(context, R.drawable.ic_baseline_circle_24)
        sec = ContextCompat.getDrawable(context, R.drawable.ic_baseline_circle_24)
        third = ContextCompat.getDrawable(context, R.drawable.ic_baseline_circle_24)
        fourth = ContextCompat.getDrawable(context, R.drawable.ic_baseline_circle_24)
    }

    private fun Drawable?.setIconAtTabPosition(index: Int) = getTabAt(index)?.apply { icon = this@setIconAtTabPosition }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setSelectedTabIndicator(null)
        tabRippleColor = null
        first.setIconAtTabPosition(0)
        sec.setIconAtTabPosition(1)
        third.setIconAtTabPosition(2)
        fourth.setIconAtTabPosition(3)
    }
}