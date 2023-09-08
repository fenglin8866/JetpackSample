package com.program.example

import android.content.Intent
import androidx.compose.runtime.Composable
import com.program.example.ExampleData.PRODUCE
import com.program.example.persistence.ui.ProductActivity
import com.program.jetpack.sample.common.MainListScreen

object ExampleData {
    const val PRODUCE = "Produce"

}

val exampleDataList = listOf(
    PRODUCE,
)

@Composable
fun ExampleMainScreen() {
    MainListScreen(exampleDataList) { content, context->
        when (content) {
            PRODUCE -> Intent(context, ProductActivity::class.java)
            else -> {
                null
            }
        }
    }
}
