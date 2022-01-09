package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@Composable
fun Header(
        title: String
) {
    Text(
            text = title,
            modifier = Modifier.semantics { heading() },
            style = MaterialTheme.typography.h5
    )
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    AnimationsCodelabTheme {
        Header("test header")
    }
}
