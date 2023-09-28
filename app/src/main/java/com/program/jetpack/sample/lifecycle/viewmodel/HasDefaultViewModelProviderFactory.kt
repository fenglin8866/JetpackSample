package com.program.jetpack.sample.lifecycle.viewmodel

interface HasDefaultViewModelProviderFactory {
    val defaultViewModelProviderFactory: ViewModelProvider.Factory
    val defaultViewModelCreationExtras: CreationExtras
        get() = CreationExtras.Empty
}