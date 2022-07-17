package ru.porcupine.whac_a_mole

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.porcupine.whac_a_mole.ui.theme.WhacAMoleTheme

class ResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var result = intent.getIntExtra("result", 0)
        var record = SharedPreference(context = this@ResultActivity).getValueInt("record")
        if(result>record){
            record=result
            SharedPreference(context = this@ResultActivity).save("record", result)
        }
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Result: ${result.toString()}")
                Text(text = "Record: ${record.toString()}")
                IconButton(
                    onClick = {
                        val changePage = Intent(this@ResultActivity, GameActivity::class.java)
                        startActivity(changePage)
                        finish()
                    },
                    modifier = Modifier.padding(15.dp)
                ) {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = "Play Again",
                    )
                }
                    IconButton(
                        onClick = {
                            val changePage = Intent(this@ResultActivity, MainActivity::class.java)
                            startActivity(changePage)
                            finish()
                        },
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Main Menu",
                        )
                    }
            }
        }
    }
}