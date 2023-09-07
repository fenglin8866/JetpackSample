package com.program.jetpack.sample.room.roomsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.program.SampleApplication
import com.program.jetpack.sample.common.ListScreen
import com.program.jetpack.sample.common.XInput
import com.program.main.ui.theme.JetpackSampleTheme

class SampleRoomActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: SampleRoomViewModel by viewModels {
                        SampleRoomViewModelFactory((application as SampleApplication).useRepository)
                    }
                    viewModel.loadUserData()
                    SampleRoomScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun SampleRoomScreen(model: SampleRoomViewModel) {
    //val usersState = model.users.collectAsStateWithLifecycle()
    val users by model.users3.observeAsState()
    Column {
        var inputStr by remember {
            mutableStateOf("")
        }
        XInput(
            value = inputStr,
            onValueChange = {
                inputStr = it
            },
            doClick = {
                // model.addUser(User(it.length, it, it))
                model.addUser2(User(it.length, it, it))
            },
            modifier = Modifier
        )

        /* ListScreen(usersState.value, trans = { user -> user.toString() }) {

         }*/
        if (users != null) {
            ListScreen(users!!, trans = { user -> user.toString() }) {

            }
        }
    }
}
