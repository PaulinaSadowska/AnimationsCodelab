package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@Composable
fun LoadingRow() {
    // TODO 5: Animate this value between 0f and 1f, then back to 0f repeatedly.
    val alpha = 1f
    Row(
            modifier = Modifier
                    .heightIn(min = 64.dp)
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
                modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray.copy(alpha = alpha))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .background(Color.LightGray.copy(alpha = alpha))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingRowPreview() {
    AnimationsCodelabTheme {
        LoadingRow()
    }
}
