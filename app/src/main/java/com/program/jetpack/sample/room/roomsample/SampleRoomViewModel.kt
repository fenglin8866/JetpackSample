package com.program.jetpack.sample.room.roomsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SampleRoomViewModel(private val userRepository: UserRepository) : ViewModel() {

    val users = userRepository.getAllUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addUser(user: User){
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }
}
class SampleRoomViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SampleRoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}