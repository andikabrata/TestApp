package com.example.testapp.persentation.feature.detail_news

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.testapp.common.extension.findActivity
import com.example.testapp.core.base.view.BaseComposeActivity
import com.example.testapp.core.ui.component.AppToolbar
import com.example.testapp.core.ui.component.Page
import com.example.testapp.core.ui.component.ToolbarAction
import com.example.testapp.data.mapper.LatestNewsToNewsModelLocalMapper
import com.example.testapp.data.model.home_news.LatestNews
import com.example.testapp.persentation.feature.notification.NotificationActivity.Companion.startNotificationActivity
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin

/**
 * @author Andika Bratadirja
 * @date 16/09/2025
 */
class DetailNewsActivity : BaseComposeActivity() {

    companion object {
        const val BUNDLE_EXTRA_NEWS = "EXTRA_NEWS"

        fun startActivity(
            context: Context,
            latestNews: LatestNews
        ) {
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra(BUNDLE_EXTRA_NEWS, latestNews)
            context.startActivity(intent)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val data: LatestNews? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(BUNDLE_EXTRA_NEWS, LatestNews::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(BUNDLE_EXTRA_NEWS)
        }

        data?.let {
            DetailNewsScreen(
                latestNews = it
            )
        }
    }
}

@Composable
fun DetailNewsScreen(
    latestNews: LatestNews,
    viewModel: DetailNewsViewModel = koinViewModel()
) {
    val mapper: LatestNewsToNewsModelLocalMapper = getKoin().get()

    DetailNewsContent(
        latestNews = latestNews,
        onbtnSaveClicked = {
            val newsModelLocal = mapper.map(it)
            viewModel.saveNews(newsModelLocal)
        }
    )
}


@Composable
fun DetailNewsContent(
    latestNews: LatestNews,
    onbtnSaveClicked: (LatestNews) -> Unit = {}
) {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            Button(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800), // background
                    contentColor = Color.White          // text/icon
                ),
                onClick = {
                    onbtnSaveClicked(latestNews)
                }
            ) {
                Text(
                    text = "Save News"
                )
            }
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(top = 20.dp, start = 30.dp, end = 30.dp)
        ) {
            AppToolbar(
                modifier = Modifier.fillMaxWidth(),
                page = Page.DETAIL,
                onActionClick = { action ->
                    when (action) {
                        ToolbarAction.BackArrow -> context.findActivity()?.finish()
                        ToolbarAction.Filter -> {}
                        ToolbarAction.Notification -> startNotificationActivity(context)
                    }
                }
            )
            Spacer(modifier = Modifier.height(26.dp))
            AsyncImage(
                model = latestNews.urlToImage,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.sample_img),
                error = painterResource(id = R.drawable.sample_img),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(174.dp)
                    .clip(RoundedCornerShape(12))
            )
            Text(
                modifier = Modifier.padding(top = 13.dp, start = 5.dp, end = 5.dp),
                text = latestNews.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            )
            Row(
                modifier = Modifier.padding(top = 20.dp),
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
                        text = latestNews.author,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFAEAEAE)
                        )
                    )
                }
                Text(
                    modifier = Modifier.padding(end = 10.dp),
                    text = formatDate(latestNews.publishedAt),
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = Color(0xFFFF9900) // orange
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier
                    .padding()
                    .verticalScroll(rememberScrollState()),
                text = latestNews.content
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailNewsContentPreview() {
    DetailNewsContent(
        LatestNews(
            urlToImage = "",
            url = "",
            title = "Dunia Sepak Bola",
            publishedAt = "2025-09-15T13:42:39Z",
            author = "andika"
        )
    )
}
