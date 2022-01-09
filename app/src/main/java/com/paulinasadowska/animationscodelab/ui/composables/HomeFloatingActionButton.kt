package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.R
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeFloatingActionButton(
        extended: Boolean,
        onClick: () -> Unit
) {
    // Use `FloatingActionButton` rather than `ExtendedFloatingActionButton` for full control on
    // how it should animate.
    FloatingActionButton(onClick = onClick) {
        Row(
                modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
            )
            // Toggle the visibility of the content with animation.
            // TODO 2-1: Animate this visibility change.
            if (extended) {
                Text(
                        text = stringResource(R.string.edit),
                        modifier = Modifier
                                .padding(start = 8.dp, top = 3.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeFloatingActionButtonPreview() {
    AnimationsCodelabTheme {
        HomeFloatingActionButton(extended = true, onClick = {})
    }
}
