package ru.porcupine.whac_a_mole

import android.content.Intent
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*


var resultCount:Int=0
var seconds:Int=0


class Game : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val timer = remember {  mutableStateOf(value = seconds)  }
            val clicksCount = remember{ mutableStateOf(value = resultCount)}
            val isMole = remember{ mutableStateOf(value = 1)}
            Counter(clicksCount = clicksCount, timer = timer)
            MainContent(clicksCount = clicksCount, molePosition = isMole)
            LaunchedEffect(key1 = Unit) {
                coroutineScope.launch() {
                   while(timer.value<30){
                       timer.value++
                       seconds=timer.value
                       delay(1000)
                       val changePage = Intent(this@Game, Result::class.java)
                       startActivity(changePage)
                   }
                }
            }
        }
    }
}

@Composable
fun MainContent(clicksCount: MutableState<Int>, molePosition: MutableState<Int>){
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for(i in 0..2){
            Column() {
                for (j in 0..2) {
                    if(molePosition.value==(i+1)*(j+1)){
                        Hole(true, clicksCount = clicksCount)
                    } else Hole(false, clicksCount = clicksCount)
                }
            }
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
fun Counter(clicksCount:MutableState<Int>, timer:MutableState<Int>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text(text= clicksCount.value.toString(), textAlign = TextAlign.Start)
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = timer.value.toString(), textAlign = TextAlign.End)
        }
    }
}
