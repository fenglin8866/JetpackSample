package com.program.jetpack.sample.paging

import android.content.Intent
import androidx.compose.runtime.*
import com.program.common.MainListScreen
import com.program.jetpack.sample.paging.PagingData.PAGING_CODELABS_ROUTE_1
import com.program.jetpack.sample.paging.PagingData.PAGING_CODELABS_ROUTE_2
import com.program.jetpack.sample.paging.PagingData.PAGING_NETWORK_ROUTE
import com.program.jetpack.sample.paging.PagingData.PAGING_SIMPLE_ROUTE
import com.program.jetpack.sample.paging.codelabs.advanced.ui.SearchRepositoriesActivity
import com.program.jetpack.sample.paging.codelabs.basic.ui.ArticleActivity
import com.program.jetpack.sample.paging.pagingsample.CheeseActivity
import com.program.jetpack.sample.paging.pagingwithnetwork.PagingNetWorkActivity

object PagingData {
    const val PAGING_SIMPLE_ROUTE = "Paging_Simple"
    const val PAGING_NETWORK_ROUTE = "Paging_Network"
    const val PAGING_CODELABS_ROUTE_1 = "Paging_Codelabs_Basic"
    const val PAGING_CODELABS_ROUTE_2 = "Paging_Codelabs_Advanced"

}

val listData = listOf(
    PAGING_SIMPLE_ROUTE,
    PAGING_NETWORK_ROUTE,
    PAGING_CODELABS_ROUTE_1,
    PAGING_CODELABS_ROUTE_2
)

@Composable
fun PagingScreen() {
    MainListScreen(listData) { content, context ->
        when (content) {
            PAGING_SIMPLE_ROUTE -> Intent(context, CheeseActivity::class.java)
            PAGING_NETWORK_ROUTE -> Intent(context, PagingNetWorkActivity::class.java)
            PAGING_CODELABS_ROUTE_1 -> Intent(context, ArticleActivity::class.java)
            PAGING_CODELABS_ROUTE_2 -> Intent(context, SearchRepositoriesActivity::class.java)
            else -> {
                null
            }
        }
    }
}


