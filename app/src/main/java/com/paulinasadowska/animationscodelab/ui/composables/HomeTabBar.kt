package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.paulinasadowska.animationscodelab.R
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme
import com.paulinasadowska.animationscodelab.ui.theme.Purple100

@Composable
fun HomeTabBar(
        backgroundColor: Color,
        tabPage: TabPage,
        onTabSelected: (tabPage: TabPage) -> Unit
) {
    TabRow(
            selectedTabIndex = tabPage.ordinal,
            backgroundColor = backgroundColor,
            indicator = { tabPositions ->
                HomeTabIndicator(tabPositions, tabPage)
            }
    ) {
        HomeTab(
                icon = Icons.Default.Home,
                title = stringResource(R.string.home),
                onClick = { onTabSelected(TabPage.Home) }
        )
        HomeTab(
                icon = Icons.Default.AccountBox,
                title = stringResource(R.string.work),
                onClick = { onTabSelected(TabPage.Work) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTabBarPreview() {
    AnimationsCodelabTheme {
        HomeTabBar(
                backgroundColor = Purple100,
                tabPage = TabPage.Home,
                onTabSelected = {}
        )
    }
}
