package com.slngl.basicstatecodelab

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * An app's "state" is any value that can change over time. This is a very broad definition and
 * encompasses everything from a Room database to a variable in a class.
 *
 * Key idea: State determines what is shown in the UI at any particular time.
 *
 */
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    val count = 0
    Text(
        text = "You've had $count glasses.",
        modifier = modifier
    )
}

/**
 * Note: It's a good practice to provide a default Modifier to all composable functions,
 * as it increases reusability. It should appear as the first optional parameter in
 * the parameter list, after all required parameters.
 */