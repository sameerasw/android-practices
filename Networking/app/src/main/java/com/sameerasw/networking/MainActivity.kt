package com.sameerasw.networking

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val posts = remember { mutableStateOf(emptyList<Post>()) }
    val isLoading = remember { mutableStateOf(false) }

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
                        isLoading.value = true
                        fetchData(
                            onPostsFetched = { fetchedPosts ->
                                posts.value = fetchedPosts
                            },
                            onLoaded = { loading ->
                                isLoading.value = loading
                            }
                        )
                    },
                ) {
                    Text(text = "Fetch")
                }
            }

            if (isLoading.value) {
                CircularProgressIndicator()
            } else {

                Row {
                    Text(
                        text = "Posts",
                    )
                }

                Row {
                    // Placeholder for the list of posts
                    PostList(posts = posts.value)
                }
            }
        }
}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
//            Text(text = "ID: ${post.id}")
//            Text(text = "User ID: ${post.userId}")
            Text(
                text = post.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = post.body)
        }
    }
}

@Composable
fun PostList(posts: List<Post>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(posts.size) { index ->
            val post = posts[index]
            PostCard(post = post)
        }
    }
}

fun fetchData(onPostsFetched: (List<Post>) -> Unit, onLoaded: (Boolean) -> Unit) {
    // should execute in background thread
    CoroutineScope(Dispatchers.IO).launch {

        // url declaration
        val uri = URL("https://jsonplaceholder.typicode.com/posts") // usually a good idea to validate the url as well especially for user input

        // create a url connection
        val connection = uri.openConnection() as HttpURLConnection
        connection.requestMethod = "GET" // set the request method to GET
        connection.connect() // connect to the url

        Log.d(TAG, "fetching data...")

        // check the response code
        val responseCode = connection.responseCode

        // check for valid response code
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Log.d(TAG, "fetchData: Response code: $responseCode") // log the response code

            // read the response and parse the data using the Post data class
            val jsonresponse = connection.inputStream.bufferedReader().use { it.readText() } // it here is the input stream

            val posts = parseJson(jsonresponse) // parse the json response

//            Log.d("Response", posts.toString()) // log the response
            withContext(Dispatchers.Main) {
                onLoaded(false) // set loading to false
                Log.d("NetworkingDemo", "fetchData: " + "${posts.count()}")
                onPostsFetched(posts) // update the UI with the fetched posts
            }
        } else {
            Log.e("Error", "Failed to fetch data: $responseCode")
            withContext(Dispatchers.Main) {
                onLoaded(false) // set loading to false
                onPostsFetched(emptyList()) // update the UI with an empty list
            }
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
