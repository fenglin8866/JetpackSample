package com.program.example

import android.content.Intent
import androidx.compose.runtime.Composable
import com.program.example.ExampleData.PRODUCE
import com.program.example.ExampleData.TODO_SAMPLE
import com.program.example.persistence.ui.ProductActivity
import com.program.example.todoapp.TodoActivity
import com.program.jetpack.sample.common.MainListScreen

object ExampleData {
    const val PRODUCE = "Produce"
    const val TODO_SAMPLE = "Todo"
}

val exampleDataList = listOf(
    PRODUCE,TODO_SAMPLE
)

@Composable
fun ExampleMainScreen() {
    MainListScreen(exampleDataList) { content, context->
        when (content) {
            PRODUCE -> Intent(context, ProductActivity::class.java)
            TODO_SAMPLE -> Intent(context, TodoActivity::class.java)
            else -> {
                null
            }
        }
    }
}
