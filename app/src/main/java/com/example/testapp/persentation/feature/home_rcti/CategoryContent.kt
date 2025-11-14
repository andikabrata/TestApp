package com.example.testapp.persentation.feature.home_rcti

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
import com.example.testapp.persentation.feature.home_rcti.data.CategoryModel

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
@Composable
fun CategoryContent(categoryModel: CategoryModel) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF282828)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
    ) {
        items(categoryModel.data) { categoryItem ->
            Column(
                modifier = Modifier
                    .width(56.dp)
                    .height(76.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF3A3A3A),
                                Color(0xFF282828)
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(0f, Float.POSITIVE_INFINITY)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = categoryItem.icon,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.sample_img_banner),
                    error = painterResource(id = R.drawable.sample_img_banner),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 40.dp, height = 38.dp)
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = categoryItem.name,
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Color(0xFF8F8F8F)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryContentPreview() {
    val categoryModel = CategoryModel.mock
    CategoryContent(categoryModel)
}