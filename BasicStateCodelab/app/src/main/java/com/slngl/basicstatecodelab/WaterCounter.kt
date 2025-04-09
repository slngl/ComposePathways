package com.slngl.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * An app's "state" is any value that can change over time. This is a very broad definition and
 * encompasses everything from a Room database to a variable in a class.
 *
 * Key idea: State determines what is shown in the UI at any particular time.
 *
 */
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}
/*@Composable
fun WaterCounter() {
    Column(modifier = Modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text("You've had $count glasses.")
        }

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }

            Button(
                onClick = { count = 0 },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Clear water count")
            }
        }
    }
}*/

/**
 * Note: It's a good practice to provide a default Modifier to all composable functions,
 * as it increases reusability. It should appear as the first optional parameter in
 * the parameter list, after all required parameters.
 */

/**
 * While the state of the app offers a description of what to display in the UI, events are
 * the mechanism through which the state changes, resulting in changes to the UI.
 *
 * Event - An event is generated by the user or another part of the program.
 * Update State - An event handler changes the state that is used by the UI.
 * Display State - The UI is updated to display the new state.
 *
 */

/**
 * recompose
 * If a state change happens, Compose re-executes the affected composable functions
 * with the new state, creating an updated UI—this is called recomposition
 *
 */

/**
 *
 * remember stores objects in the Composition, and forgets the object if the source location where
 * remember is called is not invoked again during a recomposition.
 */