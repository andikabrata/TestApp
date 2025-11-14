package com.example.testapp.persentation.feature.home_rcti

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testapp.R
import com.example.testapp.persentation.feature.home_rcti.data.BannerlModel
import com.example.testapp.persentation.feature.home_rcti.data.CategoryModel
import com.example.testapp.persentation.feature.home_rcti.data.LineupModel
import com.example.testapp.persentation.feature.home_rcti.data.StoryModel

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
class HomeRctiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    val mockDataBanner = BannerlModel.mock
    val mockDataCategory = CategoryModel.mock
    val mockDataStory = StoryModel.mock
    val mockDataLineup = LineupModel.mock
    HomeContent(
        bannerlModel = mockDataBanner,
        categoryModel = mockDataCategory,
        storyModel = mockDataStory,
        lineupModel = mockDataLineup
    )
}

@Composable
fun HomeContent(
    bannerlModel: BannerlModel,
    categoryModel: CategoryModel,
    storyModel: StoryModel,
    lineupModel: LineupModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF282828))
    ) {
        item {
            BannerContent(bannerlModel)
            Spacer(modifier = Modifier.padding(top = 16.dp))
            CategoryContent(categoryModel)
        }
        item {
            Spacer(modifier = Modifier.padding(top = 32.dp))
            StoryContent(storyModel)
        }
        items(lineupModel.data) { lineupData ->
            Spacer(modifier = Modifier.padding(top = 24.dp))
            LineupContent(lineupData)
        }
    }
}

@Composable
fun BannerContent(bannerlModel: BannerlModel) {
    val pagerState = rememberPagerState(pageCount = { bannerlModel.data.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(489.dp)
    ) {
        // Slider
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(modifier = Modifier.fillMaxSize()) {
                // Background image
                AsyncImage(
                    model = bannerlModel.data[page].portrait_image,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.sample_img_banner),
                    error = painterResource(id = R.drawable.sample_img_banner),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xFF282828).copy(alpha = 1f)),
                                startY = 900f
                            )
                        )
                )

                // Text + Button
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(42.dp)
                ) {
                    Button(
                        modifier = Modifier.width(122.dp),
                        onClick = { /* TODO: aksi klik */ },
                        shape = RoundedCornerShape(10),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF323232)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Watch Now",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp,

                                )
                        )
                    }
                }
            }
        }

        // Indicator
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(bannerlModel.data.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(if (isSelected) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.White else Color.Gray)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val mockDataBanner = BannerlModel.mock
    val categoryModel = CategoryModel.mock
    val storyModel = StoryModel.mock
    val lineupModel = LineupModel.mock
    HomeContent(mockDataBanner, categoryModel, storyModel, lineupModel)
}

