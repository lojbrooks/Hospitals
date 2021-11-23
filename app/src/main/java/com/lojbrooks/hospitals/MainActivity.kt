package com.lojbrooks.hospitals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.lojbrooks.hospitals.ui.HospitalsApp
import com.lojbrooks.hospitals.ui.theme.HospitalsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HospitalsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HospitalsApp()
                }
            }
        }
    }
}
