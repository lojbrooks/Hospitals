package com.lojbrooks.hospitals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lojbrooks.hospitals.ui.HospitalListScreen
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
