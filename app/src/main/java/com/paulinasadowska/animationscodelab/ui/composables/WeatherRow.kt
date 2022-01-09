package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulinasadowska.animationscodelab.R
import com.paulinasadowska.animationscodelab.ui.theme.Amber600
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@Composable
fun WeatherRow(
        onRefresh: () -> Unit
) {
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
                        .background(Amber600)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = stringResource(R.string.temperature), fontSize = 24.sp)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onRefresh) {
            Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.refresh)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherRowPreview() {
    AnimationsCodelabTheme {
        WeatherRow(onRefresh = {})
    }
}
