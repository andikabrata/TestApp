package com.example.testapp.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
@Composable
fun AppToolbar(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = null
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
        )
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppToolbarPreview() {
    AppToolbar(Modifier.fillMaxWidth())
}
