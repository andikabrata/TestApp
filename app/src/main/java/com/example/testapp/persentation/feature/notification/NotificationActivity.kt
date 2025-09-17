package com.example.testapp.persentation.feature.notification

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapp.common.extension.findActivity
import com.example.testapp.core.base.view.BaseComposeActivity
import com.example.testapp.core.ui.component.AppSearch
import com.example.testapp.core.ui.component.AppToolbar
import com.example.testapp.core.ui.component.Page
import com.example.testapp.core.ui.component.ToolbarAction
import com.example.testapp.data.mapper.NewsModelLocalToNewsLatestMapper
import com.example.testapp.data.model.home_news.ui_state.NewsModelLocalUiState
import com.example.testapp.persentation.feature.detail_news.DetailNewsActivity.Companion.startActivity
import com.example.testapp.persentation.feature.home_news.CategoryListNewsContent
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
class NotificationActivity : BaseComposeActivity() {
    companion object {
        fun startNotificationActivity(
            context: Context
        ) {
            val intent = Intent(context, NotificationActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        NotificationScreen()
    }
}

@Composable
fun NotificationScreen(viewModel: NotificationViewModel = koinViewModel()) {
    val mapper: NewsModelLocalToNewsLatestMapper = getKoin().get()
    val newsModelLocalUiState by viewModel.observeNewsLocal.collectAsState()
    NotificationContent(
        newsModelLocalUiState = newsModelLocalUiState,
        mapper = mapper
    )
}

@Composable
fun NotificationContent(
    newsModelLocalUiState: NewsModelLocalUiState,
    mapper: NewsModelLocalToNewsLatestMapper
) {
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    val filteredList = remember(newsModelLocalUiState.listNewsLocal, query) {
        newsModelLocalUiState.listNewsLocal
            ?.filter { item ->
                val latestNews = mapper.map(item)
                latestNews.title.contains(query, ignoreCase = true) ||
                        latestNews.author.contains(query, ignoreCase = true)
            } ?: emptyList()
    }

    Scaffold { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(top = 20.dp)
        ) {
            AppToolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                page = Page.NOTIFICATION,
                onActionClick = { action ->
                    when (action) {
                        ToolbarAction.BackArrow -> context.findActivity()?.finish()
                        ToolbarAction.Filter -> {}
                        ToolbarAction.Notification -> {}
                    }
                }
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
                onSearchClick = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(filteredList) { data ->
                    val latestNews = mapper.map(data)
                    CategoryListNewsContent(
                        data = latestNews,
                        onNewsItemClicked = {
                            startActivity(context = context, latestNews = it)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationContentPreview() {
    NotificationContent(
        newsModelLocalUiState = NewsModelLocalUiState(),
        mapper = NewsModelLocalToNewsLatestMapper()
    )
}
