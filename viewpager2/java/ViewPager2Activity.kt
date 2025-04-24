package com.xxx.xxx.xxx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.xxx.xxx.R

class ViewPager2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)
        
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val items = listOf(
            Info("Page 1", "https://fastly.picsum.photos/id/1015/600/400.jpg?hmac=tPonaXlfk6h4b60UAWLppeEvDelLS838XzWucEdHk5o"),
            Info("Page 2", "https://fastly.picsum.photos/id/1016/600/400.jpg?hmac=5WpKQcch6b9jPsbUmTUUaqDNh1rJNSk1J3ou64QfVhY"),
            Info("Page 3", "https://fastly.picsum.photos/id/1018/600/400.jpg?hmac=zv2nzzSF3GD8VhLtlhhr-2rnr9Tv46RVU6o1KfmPk9Y"),
            Info("Page 4", "https://fastly.picsum.photos/id/1019/600/400.jpg?hmac=hnITRYY9HNjnTf6hGmCrOzDjFKUG5wHisDMilWjegrE"),
            Info("Page 5", "https://fastly.picsum.photos/id/1020/600/400.jpg?hmac=PPj6tRvCC2emb9O0Z36dHmJM1EBg04og4wUl1HF1w64"),
        )
        // 设置适配器
        viewPager.adapter = GalleryAdapter(items)
        // 设置页面转换器
        viewPager.setPageTransformer(GalleryPageTransformer(viewPager))
        // 设置默认显示第二个页面
//        viewPager.currentItem = 1
        viewPager.post {// 延迟设置初始默认页面，避免页面不要切换动画效果导致第一个页面不显示
            viewPager.setCurrentItem(1, false)// 设置默认显示第二个页面且不要切换动画效果
        }
    }

}
