package com.sameerasw.intentpractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.sameerasw.intentpractices.ui.theme.IntentPracticesTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val input = intent.getStringExtra("input")
        enableEdgeToEdge()
        setContent {
            IntentPracticesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row {
                        Text(
                            text = "This is the second activity\n You searched for $input",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                    Row(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Button(
                            onClick = {
                                finish()
                            },
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            Text("Go back to the first activity")
                        }
                    }

                }
            }
        }
    }
}