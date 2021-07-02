package com.trycatch.til.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PostDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = (36 * view.resources.displayMetrics.density).toInt()
    }
}