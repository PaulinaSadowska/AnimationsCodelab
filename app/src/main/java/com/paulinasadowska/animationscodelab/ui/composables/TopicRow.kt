package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.R
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TopicRow(topic: String, expanded: Boolean, onClick: () -> Unit) {
    TopicRowSpacer(visible = expanded)
    Surface(
            modifier = Modifier
                    .fillMaxWidth(),
            elevation = 2.dp,
            onClick = onClick
    ) {
        Column(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .animateContentSize()
        ) {
            Row {
                Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                        text = topic,
                        style = MaterialTheme.typography.body1
                )
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                        text = stringResource(R.string.lorem_ipsum),
                        textAlign = TextAlign.Justify
                )
            }
        }
    }
    TopicRowSpacer(visible = expanded)
}

@Preview(showBackground = true)
@Composable
fun TopicRowPreview() {
    AnimationsCodelabTheme {
        TopicRow(topic = "test topic", expanded = true, onClick = {})
    }
}
