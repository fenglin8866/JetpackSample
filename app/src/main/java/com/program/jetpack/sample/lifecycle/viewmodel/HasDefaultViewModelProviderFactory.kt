package com.program.jetpack.sample.lifecycle.viewmodel

interface HasDefaultViewModelProviderFactory {
    /**
     * 默认Factory变量，子类实现
     */
    val defaultViewModelProviderFactory: ViewModelProvider.Factory

    /**
     * 默认CreationExtras变量
     */
    val defaultViewModelCreationExtras: CreationExtras
        get() = CreationExtras.Empty
}