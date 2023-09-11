package com.program.jetpack.sample

import androidx.compose.runtime.Composable
import com.program.common.ListScreenString

@Composable
fun JetpackMainScreen(click: (String) -> Unit) {
    ListScreenString(jetpackListData,click)
}