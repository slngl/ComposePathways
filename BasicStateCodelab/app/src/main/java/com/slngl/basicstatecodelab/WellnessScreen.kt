package com.slngl.basicstatecodelab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel(),
) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { wellnessTask, checked ->
                wellnessViewModel.changeTaskChecked(wellnessTask, checked)
            },
            onCloseTask = { task -> wellnessViewModel.remove(task) }
        )
    }
}