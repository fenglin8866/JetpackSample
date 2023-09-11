package com.program.jetpack.sample.paging

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.program.common.ListScreenString
import com.program.jetpack.sample.paging.PagingDestination.HOME_ROUTE
import com.program.jetpack.sample.paging.PagingDestination.PAGING_SIMPLE_ROUTE

object PagingDestination {
    const val HOME_ROUTE = "paging_home"
    const val PAGING_SIMPLE_ROUTE = "paging_simple"
    const val BLOG_ROUTE = "state_blog"
    const val CODELAB_ROUTE = "paging_codelab"

}

val pagingListData = listOf(PAGING_SIMPLE_ROUTE)

@Composable
fun PagingNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            ListScreenString(data = pagingListData) {
                navController.navigate(it)
            }
        }
        composable(PAGING_SIMPLE_ROUTE) {
            Log.d("xxdd","PAGING_SIMPLE_ROUTE")
            /**
             * 禁止调用非Compose逻辑。不然导致启动多个Activity。
             */
            //val intent = Intent(context, CheeseActivity::class.java)
            //ContextCompat.startActivity(context, intent, null)
        }

    }
}
