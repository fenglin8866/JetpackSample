package com.program.jetpack.sample.lifecycle.viewmodel

open class ViewModelStore {

    private val map: MutableMap<String, ViewModel> = mutableMapOf()

    fun put(key: String, viewModel: ViewModel) {
        val oldViewModel = map.put(key, viewModel)
        oldViewModel?.clear()
    }

    operator fun get(key: String): ViewModel? {
        return map[key]
    }

    fun keys(): Set<String> {
        return HashSet(map.keys)
    }

    fun clear() {
        map.forEach {
            it.value.clear()
        }
        map.clear()
    }
}

interface ViewModelStoreOwner {
    val viewModelStore: ViewModelStore
}