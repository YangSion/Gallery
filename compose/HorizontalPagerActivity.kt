package com.xxx.xxx.xxx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.xxx.xxx.xxx.ui.theme.YouTheme
import kotlin.math.absoluteValue

private data class Info(val title: String, val imgUrl: String)

private val infos = listOf(
    Info("Page 1", "https://picsum.photos/id/1015/600/400"),
    Info("Page 2", "https://picsum.photos/id/1016/600/400"),
    Info("Page 3", "https://picsum.photos/id/1018/600/400"),
    Info("Page 4", "https://picsum.photos/id/1019/600/400"),
    Info("Page 5", "https://picsum.photos/id/1020/600/400")
)

class HorizontalPagerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YouTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GalleryPager(
                        infos = infos, modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun GalleryPager(infos: List<Info>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(initialPage = 1, pageCount = { infos.size })
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // 获取屏幕宽度
//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp // 获取屏幕高度
    val pageWidth = screenWidth * 0.50f   // 每页宽度约占50%
    val contentPadding = (screenWidth - pageWidth) / 2   // 居中留边
    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = contentPadding),
            pageSpacing = 5.dp,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val offset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val absOffset = offset.absoluteValue.coerceIn(0f, 1f)
                val scale = 0.75f + (1f - absOffset) * 0.25f // 0.75~1，缩放比例
//                val heightFraction = 0.23f + (1f - absOffset) * 0.07f // 0.23~0.3，高度比例
                val alpha = 0.5f + (1 - absOffset) * 0.5f // 0.5~1，透明度
                Card(
//                    border = BorderStroke(5.dp, Color.Cyan),// 由于有缩放导致边框不能很好的适配圆角形状，会导致卡片圆角出现毛刺(锯齿/晕染边)，所以在Card中用一个Box来模拟边框
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                    modifier = Modifier
                        .width(pageWidth)
//                      .height(screenHeight * heightFraction) // 高度随偏移量变化
                        .aspectRatio(1f) // 保证是正方形
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }) {
                    Box(// 模拟边框
                        modifier = Modifier
//                            .alpha(alpha)// 设置透明度随偏移量变化(如果不需要透明度效果注释即可)
                            .fillMaxSize()
                            .background(Color.Cyan, shape = RoundedCornerShape(10.dp)/*外边圆角*/)
                            .padding(5.dp) // 模拟 border 宽度
                            .clip(RoundedCornerShape(8.dp)) // 内边圆角
                    ) {
                        AsyncImage(// 加载图片
                            model = infos[page].imgUrl,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                // page标题
//                Text(text = infos[page].title, fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YouTheme {
        GalleryPager(
            infos = infos,
        )
    }
}
