package com.program.jetpack.sample.lifecycle.viewmodel

import kotlin.reflect.KClass

@DslMarker
annotation class ViewModelFactoryDsl

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

inline fun viewModelFactory(
    builder: InitializerViewModelFactoryBuilder.() -> Unit
): ViewModelProvider.Factory = InitializerViewModelFactoryBuilder().apply(builder).build()

inline fun <reified VM : ViewModel> InitializerViewModelFactoryBuilder.initializer(
    noinline initializer: CreationExtras.() -> VM
) {
    addInitializer(VM::class, initializer)
}

