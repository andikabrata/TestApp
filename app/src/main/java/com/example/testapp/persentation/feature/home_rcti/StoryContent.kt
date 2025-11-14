package com.example.testapp.persentation.feature.home_rcti

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testapp.R
import com.example.testapp.persentation.feature.home_rcti.data.StoryModel

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
@Composable
fun StoryContent(storyModel: StoryModel) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF282828)),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(storyModel.data) { story ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(104.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.Cyan, Color.Magenta, Color.Blue)
                            ),
                            shape = CircleShape
                        )
                        .padding(3.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF282828))
                ) {
                    AsyncImage(
                        model = story.program_img,
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.ic_category),
                        error = painterResource(id = R.drawable.ic_category),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = story.title,
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(80.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoryContentPreview() {
    val storyModel = StoryModel.mock
    StoryContent(storyModel)
}