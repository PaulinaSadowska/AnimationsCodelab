package com.paulinasadowska.animationscodelab.ui.composables

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

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
    // TODO 6-1: Create an Animatable instance for the offset of the swiped element.
    pointerInput(Unit) {
        // Used to calculate a settling position of a fling animation.
        val decay = splineBasedDecay<Float>(this)
        // Wrap in a coroutine scope to use suspend functions for touch events and animation.
        coroutineScope {
            while (true) {
                // Wait for a touch down event.
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                // TODO 6-2: Touch detected; the animation should be stopped.
                // Prepare for drag events and record velocity of a fling.
                val velocityTracker = VelocityTracker()
                // Wait for drag events.
                awaitPointerEventScope {
                    horizontalDrag(pointerId) { change ->
                        // TODO 6-3: Apply the drag change to the Animatable offset.
                        // Record the velocity of the drag.
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        // Consume the gesture event, not passed to external
                        change.consumePositionChange()
                    }
                }
                // Dragging finished. Calculate the velocity of the fling.
                val velocity = velocityTracker.calculateVelocity().x
                // TODO 6-4: Calculate the eventual position where the fling should settle
                //           based on the current offset value and velocity
                // TODO 6-5: Set the upper and lower bounds so that the animation stops when it
                //           reaches the edge.
                launch {
                    // TODO 6-6: Slide back the element if the settling position does not go beyond
                    //           the size of the element. Remove the element if it does.
                }
            }
        }
    }
            .offset {
                // TODO 6-7: Use the animating offset value here.
                IntOffset(0, 0)
            }
}

@Preview(showBackground = true)
@Composable
fun TaskRowPreview() {
    AnimationsCodelabTheme {
        TaskRow(task = "test task", onRemove = {})
    }
}
