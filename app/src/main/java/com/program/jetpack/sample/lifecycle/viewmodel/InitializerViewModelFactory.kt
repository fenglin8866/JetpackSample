package com.program.jetpack.sample.lifecycle.viewmodel

import kotlin.reflect.KClass

@DslMarker
annotation class ViewModelFactoryDsl

/**
 * 定义ViewModelInitializer,InitializerViewModelFactoryBuilder,InitializerViewModelFactory的作用。
 * 通过ViewModelInitializer构建ViewModelProvider.Factory。
 * 构建方式：
 * 1.使用InitializerViewModelFactory构建，参数ViewModelInitializer
 * 2.使用InitializerViewModelFactoryBuilder构建，参数 clazz: KClass<T>, initializer: CreationExtras.() -> T
 * 3.使用viewModelFactory方法构建
 */
class ViewModelInitializer<T : ViewModel>(
    internal val clazz: Class<T>,
    internal val initializer: CreationExtras.() -> T
)

@ViewModelFactoryDsl
class InitializerViewModelFactoryBuilder {
    private val initializers = mutableListOf<ViewModelInitializer<*>>()

    fun <T : ViewModel> addInitializer(clazz: KClass<T>, initializer: CreationExtras.() -> T) {
        initializers.add(ViewModelInitializer(clazz.java, initializer))
    }

    fun build(): ViewModelProvider.Factory {
        return InitializerViewModelFactory(*initializers.toTypedArray())
    }
}

internal class InitializerViewModelFactory(
    private vararg val initializers: ViewModelInitializer<*>
) : ViewModelProvider.Factory {

    /**
     * 为什么使用ViewModelInitializer类构建ViewModel？
     */
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        var viewModel: T? = null
        @Suppress("UNCHECKED_CAST")
        initializers.forEach {
            if (it.clazz == modelClass) {
                viewModel = it.initializer.invoke(extras) as? T
            }
        }

        return viewModel ?: throw IllegalArgumentException(
            "No initializer set for given class ${modelClass.name}"
        )
    }
}


/**
 * 核心：学习其设计思想
 *
 * 代码简化：通过viewModelFactory方法构建ViewModelProvider.Factory
 *
 *  viewModelFactory {
 *          initializer(initializer)  //方法引用 -> 函数类型
 *     }
 *  或
 *   viewModelFactory {
 *         initializer { SavedStateHandlesVM() } //lambda表达式 -> 函数类型
 *     }
 */
inline fun viewModelFactory(
    builder: InitializerViewModelFactoryBuilder.() -> Unit
): ViewModelProvider.Factory = InitializerViewModelFactoryBuilder().apply(builder).build()//apply当前对象追加执行其扩展方法

inline fun <reified VM : ViewModel> InitializerViewModelFactoryBuilder.initializer(
    noinline initializer: CreationExtras.() -> VM
) {
    addInitializer(VM::class, initializer)
}
