package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopicRowSpacer(visible: Boolean) {
    AnimatedVisibility(visible = visible) {
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TopicRowSpacerPreview() {
    AnimationsCodelabTheme {
        TopicRowSpacer(visible = true)
    }
}
