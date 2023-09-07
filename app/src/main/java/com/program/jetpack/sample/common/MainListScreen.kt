package com.program.jetpack.sample.common

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun MainListScreen(data: List<String>, intentHandler: (String, Context) -> Intent?) {
    val context = LocalContext.current
    ListScreenString(data = data) { content ->
        var intent: Intent? = intentHandler(content, context)
        intent?.let {
            ContextCompat.startActivity(context, intent, null)
        }
    }
}