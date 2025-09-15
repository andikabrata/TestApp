package com.example.testapp.persentation.feature.home_news

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.testapp.R
import com.example.testapp.common.extension.DateExt.formatDate
import com.example.testapp.core.base.view.BaseComposeActivity
import com.example.testapp.core.ui.component.AppSearch
import com.example.testapp.core.ui.component.AppToolbar
import com.example.testapp.data.model.home_news.LatestNews
import org.koin.androidx.compose.koinViewModel

/**
 * @author Andika Bratadirja
 * @date 13/09/2025
 */
class HomeNewsActivity : BaseComposeActivity<HomeNewsViewModel>() {
    @Composable
    override fun Content(
        modifier: Modifier
    ) {
        HomeNewsScreen()
    }
}

@Composable
fun HomeNewsScreen(viewModel: HomeNewsViewModel = koinViewModel()) {
    val observeCategoryListNews by viewModel.observeCategoryListNews.collectAsState()
    Scaffold { scaffoldPadding ->
        val context = LocalContext.current
        var query by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(top = 20.dp)
        ) {
            AppToolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            AppSearch(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White)
                    .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(28.dp))
                    .padding(horizontal = 8.dp),
                query = query,
                onQueryChange = { query = it },
                onSearchClick = {
                    Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                item {
                    LatestNewsScreen(viewModel)
                }
                item {
                    Spacer(modifier = Modifier.height(22.dp))
                }
                item {
                    CategoryNewsScreen(
                        viewModel = viewModel,
                        onCategoryClick = {
                            viewModel.getCategoryListNews(categoryName = it)
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(observeCategoryListNews.listLatestNews ?: emptyList()) { categoryListNews ->
                    CategoryListNewsScreen(data = categoryListNews)
                }
            }
        }
    }
}

@Composable
fun LatestNewsScreen(viewModel: HomeNewsViewModel = koinViewModel()) {
    val observeLatestNews by viewModel.observeLatestNews.collectAsState()
    when {
        observeLatestNews.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp), // tetap kasih tinggi
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        observeLatestNews.errorMessage != null -> {}
        else -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                    text = "Latest News",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF027FC0)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    contentPadding = PaddingValues(start = 30.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(observeLatestNews.listLatestNews ?: emptyList()) { data ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .width(245.dp)
                                .height(166.dp),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column {
                                AsyncImage(
                                    model = data.urlToImage,
                                    contentDescription = null,
                                    placeholder = painterResource(id = R.drawable.sample_img),
                                    error = painterResource(id = R.drawable.sample_img),
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(116.dp)
                                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                                )
                            }

                            Text(
                                modifier = Modifier.padding(start = 16.dp, end = 10.dp),
                                style = TextStyle(
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.SemiBold
                                ),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                text = data.title,
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Row(
                                    modifier = Modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        modifier = Modifier.padding(start = 10.dp),
                                        painter = painterResource(id = R.drawable.ic_author),
                                        contentDescription = null
                                    )

                                    Text(
                                        modifier = Modifier.padding(start = 3.dp),
                                        text = data.author,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = TextStyle(
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFFAEAEAE)
                                        )
                                    )
                                }
                                Text(
                                    modifier = Modifier.padding(end = 10.dp),
                                    text = formatDate(data.publishedAt),
                                    style = TextStyle(
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    color = Color(0xFFFF9900) // orange
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryNewsScreen(viewModel: HomeNewsViewModel = koinViewModel(), onCategoryClick: (String) -> Unit = {}) {
    val selectedIndex by viewModel.selectedCategoryIndex
    val categoryNews by viewModel.responeGetCategoryNews.observeAsState(emptyList())

    LazyRow(
        contentPadding = PaddingValues(start = 30.dp, end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        itemsIndexed(categoryNews) { index, category ->
            val isSelected = index == selectedIndex

            Box(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        viewModel.selectedCategoryIndex.intValue = index
                        onCategoryClick(category.title)
                    }
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color(0xFFFF9800) else Color(0xFFDFDFDF),
                        shape = RoundedCornerShape(50) // full rounded
                    )
                    .background(
                        color = if (isSelected) Color(0xFFFF9800) else Color.White,
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = category.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = if (isSelected) Color.White else Color.LightGray
                    )
                )
            }
        }
    }
}

@Composable
fun CategoryListNewsScreen(data: LatestNews) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
            .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)
    ) {
        AsyncImage(
            model = data.urlToImage,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.sample_img),
            error = painterResource(id = R.drawable.sample_img),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(95.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(top = 17.dp, bottom = 17.dp, start = 7.dp)) {
                Text(
                    text = data.title,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_author),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = data.author,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(Alignment.BottomEnd),
                text = formatDate(data.publishedAt),
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                color = Color(0xFFFF9900) // orange
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeNewsScreenPreview() {
    HomeNewsScreen()
}