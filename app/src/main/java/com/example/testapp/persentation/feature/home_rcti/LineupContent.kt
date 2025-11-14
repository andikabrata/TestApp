package com.example.testapp.persentation.feature.home_rcti

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testapp.R
import com.example.testapp.persentation.feature.home_rcti.data.ContentTypeDetail
import com.example.testapp.persentation.feature.home_rcti.data.LineupContent
import com.example.testapp.persentation.feature.home_rcti.data.LineupItem
import com.example.testapp.persentation.feature.home_rcti.data.LineupModel
import com.example.testapp.persentation.feature.home_rcti.data.LiveEventContent
import com.example.testapp.persentation.feature.home_rcti.data.SpecialContent

/**
 * @author Andika Bratadirja
 * @date 21/09/2025
 */
@Composable
fun LineupContent(lineupItem: LineupItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF282828))
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = lineupItem.title,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            val lineupContents: List<LineupContent> = lineupItem.lineup_type_detail.detail.data
            val groupedByContentType: Map<String, List<LineupContent>> =
                lineupContents.groupBy { it.content_type }
            val groupedList = groupedByContentType.entries.toList()

            groupedList.forEach { (contentType, contents) ->
                when (lineupItem.display_type) {
                    "news_tag" -> {
                        items(contents) { lineupContent ->
                            DisplayNewsTag(lineupContent.content_type_detail)
                        }
                    }

                    "potrait_wt" -> {
                        items(contents) { lineupContent ->
                            DisplayPotrairWT(lineupContent.content_type_detail)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayNewsTag(contentTypeDetail: ContentTypeDetail) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF05B5F5), // biru
                        Color(0xFF0079FF)  // biru muda
                    )
                ),
                shape = RoundedCornerShape(15) // pill shape
            )
            .padding(horizontal = 12.dp, vertical = 6.dp), // ruang dalam
        contentAlignment = Alignment.Center
    ) {
        val title = when (val d = contentTypeDetail.detail.data) {
            is SpecialContent -> d.title
            is LiveEventContent -> d.title
            else -> ""
        }
        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DisplayPotrairWT(contentTypeDetail: ContentTypeDetail) {
    val title = when (val d = contentTypeDetail.detail.data) {
        is SpecialContent -> d.title
        is LiveEventContent -> d.title
        else -> ""
    }

    val image = when (val d = contentTypeDetail.detail.data) {
        is SpecialContent -> d.portrait_image
        is LiveEventContent -> d.portrait_image
        else -> ""
    }

    Column(
        modifier = Modifier
            .width(107.dp)
            .height(178.dp) // ukuran card
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.sample_img_banner),
            error = painterResource(id = R.drawable.sample_img_banner),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(159.dp)
                .clip(shape = RoundedCornerShape(7.dp))
        )

        // Judul Video
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 13.sp
            )
        )
    }
}

@Preview
@Composable
fun LineupContentPreview() {
    val mockDataLineup = LineupModel.mock
    LineupContent(mockDataLineup.data[1])
}