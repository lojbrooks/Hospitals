package com.lojbrooks.hospitals.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lojbrooks.hospitals.R

@Composable
fun HospitalsAppBar(showUpNavigation: Boolean, onNavigateUp: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        navigationIcon = if (showUpNavigation) {
            {
                IconButton(onClick = onNavigateUp) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Navigate up")
                }
            }
        } else null
    )
}
