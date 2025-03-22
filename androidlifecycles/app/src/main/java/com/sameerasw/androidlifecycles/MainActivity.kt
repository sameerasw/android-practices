package com.sameerasw.androidlifecycles

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat.getStateDescription
import com.sameerasw.androidlifecycles.ui.theme.AndroidLifecyclesTheme

/*
* An Activity in Android is a single, focused thing that the user can do.
* It’s essentially a screen in your app.
* Every application has at least one Activity, and it can contain multiple,
* each responsible for different parts of the user interface and experience.
* */
class MainActivity : ComponentActivity() {
    private val tag = "ActivityLifecycle"

    // Social Media App Lifecycle Implementation

    // Called when the Activity is first created
    // Set up the main UI layout (feed, profile, messaging tabs)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate called")
        setContent {

        }
    }

    // Called when the Activity is becoming visible to the user.
    // Begin fetching the user's feed posts from the database
    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart called")
    }

    // Called when the Activity starts interacting with the user.
    // Start auto-playing videos in the feed
    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume called")
    }

    // Called when the system is about to start another Activity.
    // Pause any playing videos, Save draft messages or posts being composed
    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause called")
    }

    // Called when the Activity is no longer visible to the user.
    // Update online status to "away" in the backend, Release resources (CPU, RAM etc) to save memory
    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop called")
    }

    // Called after the Activity has been stopped and is starting again.
    // Update the user's online status from "away" back to "online"
    // Reload friend activity indicators (who's currently active)
    // re-acquiring resources that were released during onStop()
    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart called")
    }

    // Called before the Activity is destroyed.
    // Close database connections, Remove temporary cached media files and resources
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy called")
    }

    // Additional Lifecycle Methods

    // To save temporary UI state that would disrupt the user experience if lost during a
    // configuration change (screen rotations, language, theme) or
    // when the app is temporarily killed by the system
    /* Think of it like this: Without this method, every time you rotate your phone, you would lose:
        Text you were typing in a form
        Your scroll position in a list
        Which tab you were on
        Items you had selected in checkboxes
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(tag, "onSaveInstanceState called")
    }

    // recover the exact UI state that was saved via onSaveInstanceState
    /*
    * When the activity is recreated (after rotation or being killed by the system),
    * this method gives you the opportunity to put everything back exactly as it was
        Restore cursor position in text fields
        Return to the same scroll position in a list
        Re-select the same tab
        Re-check the same checkboxes
    * */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(tag, "onRestoreInstanceState called")
    }

    /*
    * you must not use onSaveInstanceState and onRestoreInstanceState save application data
    * these lifecycle methods only to save UI state
    * */
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidLifecyclesTheme {

    }
}