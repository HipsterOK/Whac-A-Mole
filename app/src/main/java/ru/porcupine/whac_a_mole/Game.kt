package ru.porcupine.whac_a_mole

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


var resultCount:Int=0


class Game : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clicksCount = remember{ mutableStateOf(value = resultCount)}
            Counter(clicksCount = clicksCount)
            MainContent(clicksCount = clicksCount)
        }
    }
}

@Composable
fun MainContent(clicksCount: MutableState<Int>){
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Hole(true, clicksCount = clicksCount)
            Hole(false, clicksCount = clicksCount)
            Hole(false,clicksCount = clicksCount)
        }
        Column {
            Hole(false,clicksCount = clicksCount)
            Hole(false, clicksCount = clicksCount)
            Hole(false, clicksCount = clicksCount)
        }
        Column {
            Hole(false, clicksCount = clicksCount)
            Hole(false,clicksCount = clicksCount)
            Hole(false,clicksCount = clicksCount)
        }
    }
}

@Composable
fun Hole(isMole:Boolean, clicksCount:MutableState<Int>){
    val mole = remember { mutableStateOf(value = isMole) }

    IconButton(
        onClick = {
            if(mole.value){
                resultCount++
                mole.value = false
                clicksCount.value= resultCount
            }
            Log.i("Result", resultCount.toString())
        },
        modifier = Modifier.padding(15.dp)
    ) {
        var icon: ImageVector? = null
        icon = if(mole.value){
            Icons.Filled.KeyboardArrowUp
        } else{
            Icons.Filled.KeyboardArrowDown
        }
        Icon(
            icon,
            contentDescription = "hole",
        )
    }
}

@Composable
fun Counter(clicksCount:MutableState<Int>){
    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text= clicksCount.value.toString())
    }
}

fun main() = runBlocking {
    launch {
        delay(1000L)
    }
}
