package com.slngl.basicstatecodelab

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f).padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

/**
 * checkedState belongs to each WellnessTaskItem composable independently, like a private variable.
 * When checkedState changes, only that instance of WellnessTaskItem gets recomposed,
 * not all WellnessTaskItem instances in the LazyColumn.
 */
@Composable
fun WellnessTaskItem(taskName: String, modifier: Modifier = Modifier) {
    var checkedState by rememberSaveable { mutableStateOf(false) }

    WellnessTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = {},
        modifier = modifier,
    )
}

/**
 *  when an item leaves the Composition, state that was remembered is forgotten. For items on
 *  a LazyColumn, items leave the Composition entirely when you scroll past them and they're
 *  no longer visible.How do you fix this? Once again, use rememberSaveable. Your state will survive
 *  the activity or process recreation using the saved instance state mechanism. Thanks to how
 *  rememberSaveable works together with the LazyList, your items are able to also survive leaving
 *  the Composition.
 */

/**
 * The composable function rememberLazyListState creates an initial state for the list using
 * rememberSaveable. When the Activity is recreated, the scroll state is maintained without
 * you having to code anything.
 */