package com.program.jetpack.sample.lifecycle.viewmodel

interface HasDefaultViewModelProviderFactory {
    /**
     * 默认Factory变量，子类实现
     */
    val defaultViewModelProviderFactory: ViewModelProvider.Factory

    /**
     * 默认CreationExtras变量。添加默认值，相对与默认函数
     * 不能直接赋值，直接赋值，后续不能覆盖
     */
    val defaultViewModelCreationExtras: CreationExtras
        get() = CreationExtras.Empty
}