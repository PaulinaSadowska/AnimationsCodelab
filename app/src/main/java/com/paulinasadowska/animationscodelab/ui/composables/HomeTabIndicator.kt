package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.ui.theme.Green800
import com.paulinasadowska.animationscodelab.ui.theme.Purple700

@Composable
fun HomeTabIndicator(
        tabPositions: List<TabPosition>,
        tabPage: TabPage
) {
    val transition = updateTransition(targetState = tabPage, label = "tab transition")
    val indicatorLeft by transition.animateDp(
            label = "left",
            transitionSpec = {
                if (TabPage.Home isTransitioningTo TabPage.Work) {
                    // The left edge moves slower than the right edge.
                    spring(stiffness = Spring.StiffnessLow)
                } else {
                    // The left edge moves faster than the right edge.
                    spring(stiffness = Spring.StiffnessHigh)
                }
            }
    ) { page ->
        tabPositions[page.ordinal].left
    }
    val indicatorRight by transition.animateDp(label = "right") { page ->
        tabPositions[page.ordinal].right
    }
    val color by transition.animateColor(label = "color") { page ->
        if (page == TabPage.Home) Purple700 else Green800
    }

    Box(
            Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.BottomStart)
                    .offset(x = indicatorLeft)
                    .width(indicatorRight - indicatorLeft)
                    .padding(4.dp)
                    .fillMaxSize()
                    .border(
                            BorderStroke(2.dp, color),
                            RoundedCornerShape(4.dp)
                    )
    )
}