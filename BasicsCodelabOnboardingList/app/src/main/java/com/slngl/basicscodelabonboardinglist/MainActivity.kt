package com.slngl.basicscodelabonboardinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slngl.basicscodelabonboardinglist.ui.theme.BasicsCodelabOnboardingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsCodelabOnboardingListTheme {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    // shouldShowOnboarding is using a by keyword instead of the =.
    // This is a property delegate that saves you from typing .value every time.
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false }, modifier)
    } else {
        Greetings(modifier)
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

/**
 * Modifiers tell a UI element how to lay out, display, or behave within its parent layout.
 * As a best practice, your function should include a Modifier parameter that is
 * assigned an empty Modifier by default. Forward this modifier to the first composable
 * you call inside your function. This way, the calling site can adapt
 * layout instructions and behaviors from outside of your composable function.
 * */
@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000){ "$it" }
) {
// LazyColumn is a composable that arranges its children in a scrollable column.
// This is the same as using a LazyColumn inside a Column composable
//    LazyColumn renders only the visible items on screen, allowing performance gains when rendering a big list.
//    Note: LazyColumn and LazyRow are equivalent to RecyclerView in Android Views.

    LazyColumn (modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

/**
 * When a composable function is called multiple times, each call creates a separate instance of the UI element.
 * If state is created within the composable using the remember API, that state is local to that specific instance.
 * Changes to the state in one instance will not affect other instances.
 * */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    val extraPadding = if (expanded) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(extraPadding)
            ) {
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicsCodelabOnboardingListTheme {
        Greetings()
    }
}