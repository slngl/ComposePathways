package com.slngl.basicstatecodelab

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * The items method receives a key parameter. By default, each item's state is keyed against the position of the item in the list.
 *
 * In a mutable list, this causes issues when the data set changes, since items that change position effectively lose any remembered state.
 *
 * You can easily fix this by using the id of each WellnessTaskItem as the key for each item.
 */
@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckedTask : (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn( modifier = modifier ) {
        items(
            items = list,
            /**
             * Use key param to define unique keys representing the items in a mutable list,
             * instead of using the default key (list position). This prevents unnecessary
             * recompositions.
             */
            key = { task -> task.id },
        ) { task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}

/**
 * If you just define the list and then add the tasks in a different operation it would result in duplicated items being added for every recomposition.
 * Instead use the apply func to add the tasks to the list.
 *
 * val list = remember {
 *
 * mutableStateListOf<WellnessTask>().apply { addAll(getWellnessTasks()) }
 *
 * }
 */