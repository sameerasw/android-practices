package com.sameerasw.networking

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sameerasw.networking.ui.theme.NetworkingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        // A surface container using the 'background' color from the theme
                        AppContent()
                    }
                }
            }
        }
    }
}

@Composable
fun AppContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row {
                Text(
                    text = "Networking demo",
                )


            }

            Row {

                Button(
                    onClick = {
                        // Handle button click
                        fetchData()
                    },
                ) {
                    Text(text = "Fetch")
                }
            }
        }
}

fun fetchData(){
    // should execute in background thread
    CoroutineScope(Dispatchers.IO).launch {
        // Simulate network call

        // url declaration
        val uri = URL("https://jsonplaceholder.typicode.com/posts") // usually a good idea to validate the url as well especially for user input

        // create a url connection
        val connection = uri.openConnection() as HttpURLConnection
        connection.requestMethod = "GET" // set the request method to GET
        connection.connect() // connect to the url

        // check the response code
        val responseCode = connection.responseCode

        // check for valid response code
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // read the response and parse the data using the Post data class
            val jsonresponse = connection.inputStream.bufferedReader().use { it.readText() } // it here is the input stream

            val posts = parseJson(jsonresponse) // parse the json response

//            Log.d("Response", posts.toString()) // log the response
            withContext(Dispatchers.Main) {
                Log.d("NetworkingDemo", "fetchData: " + "${posts.count()}")
            }
        } else {
            Log.e("Error", "Failed to fetch data: $responseCode")
        }

    }
}

fun parseJson(json: String): List<Post> {
    val posts = mutableListOf<Post>()

    val jsonArray = JSONArray(json)

    for ( i in 0 until jsonArray.length()) {
        val postJson = jsonArray.getJSONObject(i)
        val id = postJson.getInt("id")
        val userId = postJson.getInt("userId")
        val title = postJson.getString("title")
        val body = postJson.getString("body")

        val post = Post(id, userId, title, body)

        posts.add(post)
    }

    return posts
}
