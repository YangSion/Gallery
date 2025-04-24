package com.xxx.xxx.xxx

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.xxx.xxx.R
import kotlin.math.abs

class GalleryPageTransformer(val viewPager: ViewPager2) : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        // 设置间距和页边可见
        val pageMarginPx = viewPager.resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val offsetPx = viewPager.resources.getDimensionPixelOffset(R.dimen.offset)
        // 设置缩放比例
        val scale = 0.75f + (1 - abs(position)) * 0.25f// 0.75~1，缩放比例
        page.scaleY = scale
        page.scaleX = scale
        val offset = position * -(2 * offsetPx + pageMarginPx)
        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (viewPager.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
                page.translationX = -offset
            } else {
                page.translationX = offset
            }
        } else {
            page.translationY = offset
        }
        // 设置透明度效果(如果不需要透明度效果注释即可)
//        page.alpha = 0.5f + (1 - abs(position)) * 0.5f// 0.5~1，透明度
        // 设置 page 的 padding 和 clipToPadding
        val recyclerView = viewPager.getChildAt(0) as RecyclerView
        val padding = offsetPx
        recyclerView.setPadding(padding, 0, padding, 0)
        recyclerView.clipToPadding = false
    }
}
