package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.R
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EditMessage(shown: Boolean) {
    // TODO 2-2: The message should slide down from the top on appearance and slide up on
    //           disappearance.
    AnimatedVisibility(
            visible = shown,
            enter = slideInVertically(
                    initialOffsetY = { fullHeight -> (-fullHeight) },
                    animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
            ),
            exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> (-fullHeight) },
                    animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
            )
    ) {
        Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.secondary,
                elevation = 4.dp
        ) {
            Text(
                    text = stringResource(R.string.edit_message),
                    modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditMessagePreview() {
    AnimationsCodelabTheme {
        EditMessage(shown = true)
    }
}
