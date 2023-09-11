package com.program.thinking

import androidx.compose.runtime.Composable
import com.program.common.ListScreenString

@Composable
fun ThinkingMainScreen(click: (String) -> Unit) {
    ListScreenString(thinkingListData,click)
}