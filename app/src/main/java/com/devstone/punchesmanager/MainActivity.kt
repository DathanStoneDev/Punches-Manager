package com.devstone.punchesmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.devstone.punchesmanager.util.navigation.MainEntry
import com.devstone.punchesmanager.ui.theme.PunchesManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PunchesManagerTheme {
                MainEntry()
            }
        }
    }
}
