package com.slngl.basiclayoutsincompose

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.slngl.basiclayoutsincompose.ui.theme.BasicLayoutsInComposeTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicLayoutsInComposeTheme {
                // Scaffold gives you a top-level configurable composable for apps that implement
                // Material design. It contains slots for various Material concepts,
                // one of which is the bottom bar.
                Scaffold(bottomBar = { SootheBottomNav() }) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * When writing composables, you use modifiers to:
 *
 * Change the composable's size, layout, behavior, and appearance.
 * Add information, like accessibility labels.
 * Process user input.
 * Add high-level interactions, like making an element clickable, scrollable,
 * draggable, or zoomable.
 */
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(value = "",
        onValueChange = {},
        placeholder = { Text(stringResource(R.string.placeholder_search)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "null"
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedLabelColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        trailingIcon = { /* ... */ },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

/**
 * In general, to align composables inside a parent container, you set the alignment of that parent container. So instead of telling the child to position itself in its parent, you tell the parent how to align its children.
 *
 * For a Column, you decide how its children should be aligned horizontally. The options are:
 *
 * Start
 * CenterHorizontally
 * End
 * For a Row, you set the vertical alignment. The options are similar to those of the Column:
 *
 * Top
 * CenterVertically
 * Bottom
 * For a Box, you combine both horizontal and vertical alignment. The options are:
 *
 * TopStart
 * TopCenter
 * TopEnd
 * CenterStart
 * Center
 * CenterEnd
 * BottomStart
 * BottomCenter
 * BottomEnd
 */
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(text),
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * Surface is a component in the Compose Material library. It follows general
 * Material Design patterns and you can adapt it by changing your app's theme.
 */
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int, @StringRes text: Int, modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

/**
 * Used to specify the arrangement of the layout's children in layouts
 * like Row or Column in the main axis direction (horizontal and vertical, respectively).
 */

@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(
                drawable = item.drawable, text = item.text
            )
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.drawable, item.text, Modifier.height(80.dp))
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }


private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int, @StringRes val text: Int
)

/**
 * Slot API
 *
 * You can use the content parameter for the composable's slot.
 * This way, when you use the HomeSection composable,
 * you can use a trailing lambda to fill the content slot. When a composable provides multiple slots
 * to fill in, you can give them meaningful names that represent their function in the
 * bigger composable container. For example, Material's TopAppBar provides the slots for title,
 * navigationIcon, and actions.
 *
 */
@Composable
fun HomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

/**
 *
 * manually scroll
 *
 * Lazy layouts such as LazyRow and LazyHorizontalGrid automatically add scrolling behavior.
 * However, you don't always need a Lazy layout. In general, you use a Lazy layout when
 * you have many elements in a list or large data sets to load, so emitting all items
 * at once would come at a performance cost and would slow down your app. When a list has
 * only a limited number of elements, you can instead choose to use a simple Column or Row
 * and add the scroll behavior manually. To do so, you use the verticalScroll or horizontalScroll
 * modifiers. These require a ScrollState, which contains the current state of the scroll,
 * used to modify the scroll state from outside. In this case, you're not looking to
 * modify the scroll state, so you simply create a persistent ScrollState instance using
 * rememberScrollState.
 *
 */
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun SootheBottomNav(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier
    ) {
        NavigationBarItem(icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null
            )
        }, label = {
            Text(
                text = stringResource(R.string.bottom_navigation_home)
            )
        }, selected = true, onClick = { })
        NavigationBarItem(icon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
        },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = false,
            onClick = { })
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    BasicLayoutsInComposeTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    BasicLayoutsInComposeTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicLayoutsInComposeTheme {
        FavoriteCollectionCard(
            text = R.string.fc2_nature_meditations,
            drawable = R.drawable.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    BasicLayoutsInComposeTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyRowPreview() {
    BasicLayoutsInComposeTheme {
        AlignYourBodyRow()
    }
}

@Preview(showBackground = true)
@Composable
fun AlignYourBodyElementPreview() {
    BasicLayoutsInComposeTheme {
        AlignYourBodyElement(
            text = R.string.ab1_inversions,
            drawable = R.drawable.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}