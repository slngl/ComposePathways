package com.slngl.basicstatecodelab

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

/**
 * ViewModels provide the UI state and access to the business logic located in
 * other layers of the app. Additionally, ViewModels survive configuration changes,
 * so they have a longer lifetime than the Composition. They can follow the lifecycle
 * of the host of Compose content—that is, activities, fragments, or the destination
 * of a Navigation graph if you're using Compose Navigation.
 */
class WellnessViewModel : ViewModel() {
    /**
     * Don't expose the mutable list of tasks from outside the ViewModel.
     * Instead define _tasks and tasks. _tasks is internal and mutable inside the ViewModel.
     * tasks is public and read-only.
     */
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks : List<WellnessTask>
        get() = _tasks

    fun remove(task: WellnessTask) {
        _tasks.remove(task)
    }

    fun changeTaskChecked(task: WellnessTask, checked: Boolean) {
        _tasks.find {
            it.id == task.id
        }?.let {
            it.checked = checked
        }
    }
}

fun getWellnessTasks(): List<WellnessTask> = List(30) { i -> WellnessTask(i, "Task # $i") }
