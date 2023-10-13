package com.program.jetpack.sample.lifecycle.viewmodel

import kotlin.reflect.KClass

/**
 * 定义ViewModelInitializer,InitializerViewModelFactoryBuilder,InitializerViewModelFactory的作用。
 * 通过ViewModelInitializer构建ViewModelProvider.Factory。
 * 构建方式：
 * 1.使用InitializerViewModelFactory构建，参数ViewModelInitializer
 * 2.使用InitializerViewModelFactoryBuilder构建，参数 clazz: KClass<T>, initializer: CreationExtras.() -> T
 * 3.使用viewModelFactory方法构建
 */

@DslMarker
annotation class ViewModelFactoryDsl

/**
 * 功能：封装ViewModel的Class类型和函数类型（根据Creation中参数构建ViewModel）
 *      理解的核心：函数类型是代码片段。完成某些小功能，封装是小细节，用于类之间的交互
 * 作用：快速构建ViewModel。
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

/**
 *
 *   val factory = viewModelFactory {
 *     initializer { TestViewModel(this[key]) }
 *   }
 *   val viewModel: TestViewModel = factory.create(TestViewModel::class.java, extras) //只是构建了ViewModel，没有与对象关联。
 *   或
 *   val viewModel: TestViewModel = ViewModelProvider(this,factory,extras).get(TestViewModel::class.java)
 */

internal class InitializerViewModelFactory(
    private vararg val initializers: ViewModelInitializer<*>
) : ViewModelProvider.Factory {

    /**
     * 为什么使用ViewModelInitializer类构建ViewModel？
     * 原因：简化构建
     */
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        var viewModel: T? = null
        @Suppress("UNCHECKED_CAST")
        initializers.forEach {
            if (it.clazz == modelClass) {
                /**
                 * 扩展函数类型是CreationExtras.() -> T，无参方法，为什么调用的时候必须传入对象？
                 * 原因：initializer是函数类型，但依赖CreationExtras对象，该类型是参数没有实际定义，无法直接通过对象调用。
                 * 需要通过反射调用执行。
                 *
                 * 什么不直接使用对象直接调用？
                 * 原因：该类型是参数，没有实际定义，对象无法直接调用。
                 *
                 * 如果一定要通过extras调用怎么处理?
                 * 处理方式1：
                 * CreationExtras定义方法
                 *   fun a(b:CreationExtras.()->ViewModel){
                 *         b()
                 *     }
                 * extras.a(it.initializer)
                 * 但这种处理，感觉是多此一举，不够简洁，而且失去CreationExtras.() -> T灵活性，扩展性。
                 *
                 * 方式2：
                 * 如果定义的扩展函数类型参数与库作用域函数参数一致，可以直接使用作用域函数。
                 *
                 */
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
 * 代码简化：简化Factory的构建，通过kotlin特性快速构建Factory
 * 通过viewModelFactory方法构建ViewModelProvider.Factory
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
