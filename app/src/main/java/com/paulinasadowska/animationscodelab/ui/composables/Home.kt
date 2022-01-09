package com.paulinasadowska.animationscodelab.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulinasadowska.animationscodelab.R
import com.paulinasadowska.animationscodelab.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TabPage {
    Home, Work
}

/**
 * Shows the entire screen.
 */
@Composable
fun Home() {
    // String resources.
    val allTasks = stringArrayResource(R.array.tasks)
    val allTopics = stringArrayResource(R.array.topics).toList()

    // The currently selected tab.
    var tabPage by remember { mutableStateOf(TabPage.Home) }

    // True if the whether data is currently loading.
    var weatherLoading by remember { mutableStateOf(false) }

    // Holds all the tasks currently shown on the task list.
    val tasks = remember { mutableStateListOf(*allTasks) }

    // Holds the topic that is currently expanded to show its body.
    var expandedTopic by remember { mutableStateOf<String?>(null) }

    // True if the message about the edit feature is shown.
    var editMessageShown by remember { mutableStateOf(false) }

    // Simulates loading weather data. This takes 3 seconds.
    suspend fun loadWeather() {
        if (!weatherLoading) {
            weatherLoading = true
            delay(3000L)
            weatherLoading = false
        }
    }

    // Shows the message about edit feature.
    suspend fun showEditMessage() {
        if (!editMessageShown) {
            editMessageShown = true
            delay(3000L)
            editMessageShown = false
        }
    }

    // Load the weather at the initial composition.
    LaunchedEffect(Unit) {
        loadWeather()
    }

    val lazyListState = rememberLazyListState()

    // The background color. The value is changed by the current tab.
    // TODO 1: Animate this color change.
    val backgroundColor = if (tabPage == TabPage.Home) Purple100 else Green300

    // The coroutine scope for event handlers calling suspend functions.
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
            topBar = {
                HomeTabBar(
                        backgroundColor = backgroundColor,
                        tabPage = tabPage,
                        onTabSelected = { tabPage = it }
                )
            },
            backgroundColor = backgroundColor,
            floatingActionButton = {
                HomeFloatingActionButton(
                        extended = lazyListState.isScrollingUp(),
                        onClick = {
                            coroutineScope.launch {
                                showEditMessage()
                            }
                        }
                )
            }
    ) {
        LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
                state = lazyListState
        ) {
            // Weather
            item { Header(title = stringResource(R.string.weather)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Surface(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = 2.dp
                ) {
                    if (weatherLoading) {
                        LoadingRow()
                    } else {
                        WeatherRow(onRefresh = {
                            coroutineScope.launch {
                                loadWeather()
                            }
                        })
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // Topics
            item { Header(title = stringResource(R.string.topics)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(allTopics) { topic ->
                TopicRow(
                        topic = topic,
                        expanded = expandedTopic == topic,
                        onClick = {
                            expandedTopic = if (expandedTopic == topic) null else topic
                        }
                )
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }

            // Tasks
            item { Header(title = stringResource(R.string.tasks)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            if (tasks.isEmpty()) {
                item {
                    TextButton(onClick = { tasks.clear(); tasks.addAll(allTasks) }) {
                        Text(stringResource(R.string.add_tasks))
                    }
                }
            }
            items(count = tasks.size) { i ->
                val task = tasks.getOrNull(i)
                if (task != null) {
                    key(task) {
                        TaskRow(
                                task = task,
                                onRemove = { tasks.remove(task) }
                        )
                    }
                }
            }
        }
        EditMessage(editMessageShown)
    }
}

/**
 * Returns whether the lazy list is currently scrolling up.
 */
@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

/**
 * Shows an indicator for the tab.
 *
 * @param tabPositions The list of [TabPosition]s from a [TabRow].
 * @param tabPage The [TabPage] that is currently selected.
 */
@Composable
fun HomeTabIndicator(
        tabPositions: List<TabPosition>,
        tabPage: TabPage
) {
    // TODO 4: Animate these value changes.
    val indicatorLeft = tabPositions[tabPage.ordinal].left
    val indicatorRight = tabPositions[tabPage.ordinal].right
    val color = if (tabPage == TabPage.Home) Purple700 else Green800
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

@Preview
@Composable
private fun PreviewHome() {
    AnimationsCodelabTheme {
        Home()
    }
}
