package com.sameerasw.intentpractices

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.sameerasw.intentpractices.ui.theme.IntentPracticesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentPracticesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        context = this,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(context: Context, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var displaytext by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Row {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Input") },
                modifier = Modifier.padding(2.dp)
            )
            Button(
                onClick = {
                    displaytext = text

                    // implicit intent (Opens the browser with the search query)
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://www.google.com/search?q=$displaytext")
                    }
                    ContextCompat.startActivity(context, intent, null)
                },
                modifier = Modifier.padding(2.dp)
            ) {
                Text("Search Google")
            }

            IconButton (
                onClick = {
                    displaytext = text

                    // explicit intent (Prompts user to select an app to share)
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "Hello, $displaytext!")
                        type = "text/plain"
                    }
                    ContextCompat.startActivity(context, Intent.createChooser(shareIntent, null), null)
                },
                modifier = Modifier.padding(2.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share"
                )
            }

        }
        
        if (displaytext.isNotEmpty()) {
            Text(
                text = "Searching for, $displaytext",
                modifier = Modifier.padding(8.dp)
            )
        }

        Row {
            Button(
                onClick = {
                    displaytext = text

                    // explicit intent (Opens the SecondActivity passing the input text data)
                    val intent = Intent(context, SecondActivity::class.java)
                    intent.putExtra("input", displaytext)
                    ContextCompat.startActivity(context, intent, null)
                },
                modifier = Modifier.padding(2.dp)
            ) {
                Text("Open Second Activity")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentPracticesTheme {
        Greeting(context = LocalContext.current)
    }
}