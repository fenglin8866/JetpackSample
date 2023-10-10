package com.program.jetpack.sample.lifecycle

import com.program.jetpack.sample.lifecycle.viewmodel.HasDefaultViewModelProviderFactory
import com.program.jetpack.sample.lifecycle.viewmodel.ViewModelProvider
import com.program.jetpack.sample.lifecycle.viewmodel.ViewModelStore
import com.program.jetpack.sample.lifecycle.viewmodel.ViewModelStoreOwner

class MyFragment(
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory,
    override val viewModelStore: ViewModelStore
) :ViewModelStoreOwner,HasDefaultViewModelProviderFactory {

}