package ru.porcupine.whac_a_mole

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var record = 0
        setContent {
            val context = LocalContext.current
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        val changePage = Intent(this@MainActivity, Game::class.java)
                        startActivity(changePage)
                    },
                    modifier = Modifier.padding(15.dp)


                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "Refresh Button",
                    )
                }
                Text(
                    text = "Record: $record",
                    modifier = Modifier.padding(15.dp)
                )
                Text(
                    text = getString(R.string.instruction),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(15.dp)
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}