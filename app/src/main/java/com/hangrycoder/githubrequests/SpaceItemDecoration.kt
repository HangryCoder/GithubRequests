package com.hangrycoder.githubrequests

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration(private val spaceHeight: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceHeight
        }

        outRect.left = spaceHeight
        outRect.right = spaceHeight

        val itemCount = parent.adapter?.itemCount ?: 0
        if (parent.getChildAdapterPosition(view) != itemCount - 1) {
            outRect.bottom = spaceHeight
        }
    }
}