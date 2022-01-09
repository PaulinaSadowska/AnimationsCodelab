package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@Composable
fun HomeTab(
        icon: ImageVector,
        title: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier
) {
    Row(
            modifier = modifier
                    .clickable(onClick = onClick)
                    .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
                imageVector = icon,
                contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTabPreview() {
    AnimationsCodelabTheme {
        HomeTab(
                icon = Icons.Default.Home,
                title = "Home",
                onClick = { }
        )
    }
}
