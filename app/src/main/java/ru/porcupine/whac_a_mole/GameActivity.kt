package ru.porcupine.whac_a_mole

import android.content.Intent
import android.icu.number.Scale
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import java.util.*
import kotlin.random.Random

var resultCount:Int=0
var seconds:Int=0
var holeId:Int=0

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        resultCount=0
        seconds=0
        holeId=0

        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val timer = remember {  mutableStateOf(value = seconds)  }
            val clicksCount = remember{ mutableStateOf(value = resultCount)}
            val isMole = remember{ mutableStateOf(value = 0)}
            BackImage()
            Counter(clicksCount = clicksCount, timer = timer)
            MainContent(clicksCount = clicksCount, molePosition = isMole)
            LaunchedEffect(key1 = Unit) {
                coroutineScope.launch() {
                   while(timer.value<30){
                       timer.value++
                       seconds=timer.value
                       delay(1000)
                   }
                    val changePage = Intent(this@GameActivity, ResultActivity::class.java)
                    changePage.putExtra("result", clicksCount.value);
                    startActivity(changePage)
                    finish()
                }
                coroutineScope.launch() {
                    var tmp:Int
                    while (true) {
                        tmp= (0..8).random()
                        while (tmp==isMole.value){
                            tmp=(0..8).random()
                        }
                        isMole.value = tmp
                        delay(500)
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
        verticalAlignment = Alignment.CenterVertically,

    ) {
        for(i in 0..2){
            Column() {
                for (j in 0..2) {
                    Hole(molePosition, clicksCount = clicksCount)
                    holeId++
                }
                }
            }
        }
    }

@Composable
fun Hole(molePosition: MutableState<Int>, clicksCount:MutableState<Int>){
    val holeId= remember {  mutableStateOf(value = holeId)  }
    Log.i("Position", holeId.value.toString())
    IconButton(
        onClick = {
            if(molePosition.value==holeId.value){
                resultCount++
                clicksCount.value= resultCount
            }
            Log.i("Result", resultCount.toString())
        },
        modifier = Modifier.padding(15.dp)
    ) {
        if(molePosition.value==holeId.value){
            Icon(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.mole),
                contentDescription = "mole",
                tint = Color.Unspecified
            )
        } else{
            Icon(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.hole),
                contentDescription = "hole",
                tint = Color.Unspecified
            )
        }

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
            Text(
                text= "Result: ${clicksCount.value.toString()}",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(
                text = "Time: ${timer.value.toString()}",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }
    }
}

@Composable
fun BackImage() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "back",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
