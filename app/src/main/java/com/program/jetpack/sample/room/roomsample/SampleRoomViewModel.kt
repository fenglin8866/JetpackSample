package com.program.jetpack.sample.room.roomsample

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SampleRoomViewModel(private val userRepository: UserRepository) : ViewModel() {

    val users = userRepository.getAllUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    /**
     * 数据库内部关联LiveData，当发生修改时，自动更新LiveData，
     * 同时实现了异步，使用postValue
     */
    private val _users2 = userRepository.getAllUser2()
    val users2: LiveData<List<User>> = _users2

    /**
     * 数据库不是可观察类型，数据库变化，不会自动同步，需要主动查询
     * 例如：_users3.postValue(userRepository.getAllUser3())
     */
    private val _users3 = MutableLiveData<List<User>>()
    val users3: LiveData<List<User>> = _users3

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }

    fun addUser2(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userRepository.addUser2(user)
                _users3.postValue(userRepository.getAllUser3())
            }
        }
    }

    fun loadUserData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _users3.postValue(userRepository.getAllUser3())
            }
        }
    }

}

class SampleRoomViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SampleRoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}