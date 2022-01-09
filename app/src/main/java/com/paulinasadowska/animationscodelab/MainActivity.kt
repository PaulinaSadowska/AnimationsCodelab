package com.paulinasadowska.animationscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.paulinasadowska.animationscodelab.ui.composables.Home
import com.paulinasadowska.animationscodelab.ui.theme.AnimationsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsCodelabTheme {
                Home()
            }
        }
    }
}