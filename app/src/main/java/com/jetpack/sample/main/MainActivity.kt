package com.jetpack.sample.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jetpack.sample.main.theme.JetpackSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavGraph(rememberNavController())
                }
            }
        }
    }
}



@Composable
fun MainScreen(click:(String)->Unit) {
    LazyColumn {
        items(SampleData.conversationSample, key = { item -> item.id }) { item ->
            MainItem(item.title,click)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainItem(title: String, click:(String)->Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .wrapContentSize(),
        onClick = {
            click(title)
        }
    ) {
        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 10.dp)
                .wrapContentSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackSampleTheme {
       // MainScreen()
    }
}