package com.program.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LanguagePrintScreen(click: () -> Unit) {
    Column {
        Button(onClick = { click() }) {
            Text(text = "运行")
        }
    }
}