package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun TaskRow(task: String, onRemove: () -> Unit) {
    Surface(
            modifier = Modifier
                    .fillMaxWidth()
                    .swipeToDismiss(onRemove),
            elevation = 2.dp
    ) {
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
        ) {
            Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                    text = task,
                    style = MaterialTheme.typography.body1
            )
        }
    }
}

private fun Modifier.swipeToDismiss(
        onDismissed: () -> Unit
): Modifier = composed {

    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        // Used to calculate a settling position of a fling animation.
        val decay = splineBasedDecay<Float>(this)
        // Wrap in a coroutine scope to use suspend functions for touch events and animation.
        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                offsetX.stop()
                // Prepare for drag events and record velocity of a fling.
                val velocityTracker = VelocityTracker()
                // Wait for drag events.
                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->
                        val horizontalDragOffset = offsetX.value + change.positionChange().x
                        launch {
                            offsetX.snapTo(horizontalDragOffset)
                        }
                        // Record the velocity of the drag.
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        // Consume the gesture event, not passed to external
                        change.consumePositionChange()
                    }
                }
                // Dragging finished. Calculate the velocity of the fling.
                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)
                offsetX.updateBounds(
                        lowerBound = -size.width.toFloat(),
                        upperBound = size.width.toFloat()
                )
                launch {
                    if (targetOffsetX.absoluteValue < size.width) {
                        offsetX.animateTo(targetValue = 0f, initialVelocity = velocity)
                    } else {
                        offsetX.animateDecay(velocity, decay)
                        onDismissed()
                    }
                }
            }
        }
    }.offset {
        IntOffset(offsetX.value.roundToInt(), 0)
    }
}

@Preview(showBackground = true)
@Composable
fun TaskRowPreview() {
    AnimationsCodelabTheme {
        TaskRow(task = "test task", onRemove = {})
    }
}
