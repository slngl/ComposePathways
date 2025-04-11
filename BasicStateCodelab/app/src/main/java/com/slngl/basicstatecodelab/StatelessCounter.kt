package com.slngl.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 *
 * Composables with internal state tend to be less reusable and harder to test.
 *
 * Composables that don't hold any state are called stateless composables. An easy way to create
 * a stateless composable is by using state hoisting.
 *
 * State hoisting in Compose is a pattern of moving state to a composable's caller to make
 * a composable stateless. The general pattern for state hoisting in Jetpack Compose is to replace
 * the state variable with two parameters:
 *
 * value: T - the current value to display
 * onValueChange: (T) -> Unit - an event that requests the value to change with a new value T
 * where this value represents any state that could be modified.
 */
@Composable
fun StatelessCounter( count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = onIncrement, modifier = Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)
}

/**
 *
 * Key Point: When hoisting state, there are three rules to help you figure out where state should go:
 *
 * State should be hoisted to at least the lowest common parent of all composables that use the state (read).
 * State should be hoisted to at least the highest level it may be changed (write).
 * If two states change in response to the same events they should be hoisted to the same level.
 * You can hoist the state higher than these rules require, but if you don't hoist the state high enough,
 * it might be difficult or impossible to follow unidirectional data flow.
 */