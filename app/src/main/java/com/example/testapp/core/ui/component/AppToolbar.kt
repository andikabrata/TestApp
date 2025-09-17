package com.example.testapp.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.testapp.R

/**
 * @author Andika Bratadirja
 * @date 14/09/2025
 */
enum class Page {
    HOME,
    DETAIL,
    NOTIFICATION
}

@Composable
fun AppToolbar(
    modifier: Modifier,
    page: Page,
    onActionClick: (ToolbarAction) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(
                id = if (page == Page.HOME) R.drawable.ic_filter else R.drawable.ic_arrow_left
            ),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    if (page != Page.HOME) {
                        onActionClick(ToolbarAction.BackArrow)
                    } else {
                        onActionClick(ToolbarAction.Filter)
                    }
                }
        )

        Text(
            text = "NEWS",
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                shadow = Shadow(
                    color = Color.Gray,
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            ),
            modifier = Modifier.align(Alignment.Center)
        )

        if (page != Page.NOTIFICATION) {
            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onActionClick(ToolbarAction.Notification)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppToolbarPreview() {
    AppToolbar(Modifier.fillMaxWidth(), page = Page.HOME)
}
