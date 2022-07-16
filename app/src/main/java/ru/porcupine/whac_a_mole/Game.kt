package ru.porcupine.whac_a_mole

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.porcupine.whac_a_mole.ui.theme.WhacAMoleTheme

class Game : ComponentActivity() {

    var time:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent(){
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Hole()
            Hole()
            Hole()
        }
        Column {
            Hole()
            Hole()
            Hole()
        }
        Column {
            Hole()
            Hole()
            Hole()
        }
    }
}

@Composable
fun Hole(){
    IconButton(
        onClick = {

        },
        modifier = Modifier.padding(15.dp)
    ) {
        Icon(
            Icons.Filled.KeyboardArrowDown,
            contentDescription = "hole",
        )
    }
}

fun main() = runBlocking {
    launch {
        delay(1000L)
    }
}
