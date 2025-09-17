package com.example.testapp.persentation.feature.search

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
import com.example.testapp.data.model.home_news.ui_state.LatestNewsModelUiState
import com.example.testapp.persentation.feature.detail_news.DetailNewsActivity.Companion.startActivity
import com.example.testapp.persentation.feature.home_news.CategoryListNewsContent
import com.example.testapp.persentation.feature.notification.NotificationActivity.Companion.startNotificationActivity
import org.koin.androidx.compose.koinViewModel

/**
 * @author Andika Bratadirja
 * @date 17/09/2025
 */
class SearchNewsActivity : BaseComposeActivity() {
    companion object {
        fun startSearchActivity(
            context: Context
        ) {
            val intent = Intent(context, SearchNewsActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        SearchNewsSreen()
    }
}

@Composable
fun SearchNewsSreen(viewModel: SearchNewsViewModel = koinViewModel()) {
    val latestNewsModelUiState by viewModel.observeLatestNews.collectAsState()
    SearchNewsContent(
        latestNewsModelUiState = latestNewsModelUiState,
        onSearchClicked = {
            viewModel.getSearchNews(kewyword = it)
        }
    )
}

@Composable
fun SearchNewsContent(
    latestNewsModelUiState: LatestNewsModelUiState,
    onSearchClicked: (String) -> Unit = {}
) {
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
                    .padding(start = 30.dp, end = 30.dp),
                page = Page.DETAIL,
                onActionClick = { action ->
                    when (action) {
                        ToolbarAction.BackArrow -> context.findActivity()?.finish()
                        ToolbarAction.Filter -> {}
                        ToolbarAction.Notification -> startNotificationActivity(context)
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
                onSearchClick = {
                    onSearchClicked(query)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(latestNewsModelUiState.listLatestNews ?: emptyList()) { data ->
                    CategoryListNewsContent(
                        data = data,
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
fun SearchNewsContentPreview() {
    SearchNewsContent(LatestNewsModelUiState())
}
