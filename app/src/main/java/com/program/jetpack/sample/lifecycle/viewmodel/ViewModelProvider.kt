package com.program.jetpack.sample.lifecycle.viewmodel

import android.app.Application
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelProvider
import com.program.jetpack.sample.lifecycle.viewmodel.ViewModelProvider.AndroidViewModelFactory.Companion.DEFAULT_KEY
import com.program.jetpack.sample.lifecycle.viewmodel.ViewModelProvider.AndroidViewModelFactory.Companion.defaultFactory
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.lang.reflect.InvocationTargetException


open class ViewModelProvider @JvmOverloads constructor(
    private val store: ViewModelStore,
    private val factory: Factory,
    private val defaultCreationExtras: CreationExtras = CreationExtras.Empty
) {
    constructor(owner: ViewModelStoreOwner) : this(
        owner.viewModelStore,
        defaultFactory(owner),
        defaultCreationExtras(owner)
    )

    constructor(owner: ViewModelStoreOwner, factory: Factory) : this(
        owner.viewModelStore,
        factory,
        defaultCreationExtras(owner)
    )

    interface Factory {
        /**
         * 根据类名构建ViewModel对象
         */
        fun <T : ViewModel> create(modelClass: Class<T>): T {
            throw UnsupportedOperationException(
                "Factory.create(String) is unsupported.  This Factory requires " +
                        "`CreationExtras` to be passed into `create` method."
            )
        }

        fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
            create(modelClass)

        companion object {
            @JvmStatic
            fun from(vararg initializers: ViewModelInitializer<*>): Factory {
                return InitializerViewModelFactory(*initializers)
            }
        }
    }

    @Suppress("SingletonConstructor")
    open class NewInstanceFactory : Factory {
        @Suppress("DocumentExceptions")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return try {
                modelClass.getDeclaredConstructor().newInstance()
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }

        companion object {
            private var sInstance: NewInstanceFactory? = null

            @JvmStatic
            val instance: NewInstanceFactory
                get() {
                    if (sInstance == null) {
                        sInstance = NewInstanceFactory()
                    }
                    return sInstance!!
                }

            private object ViewModelKeyImpl : CreationExtras.Key<String>


            @JvmField
            val VIEW_MODEL_KEY: CreationExtras.Key<String> = ViewModelKeyImpl
        }
    }

    /**
     * 参数unused的作用？
     */
    open class AndroidViewModelFactory private constructor(
        private val application: Application?,
        // parameter to avoid clash between constructors with nullable and non-nullable
        // Application
        @Suppress("UNUSED_PARAMETER") unused: Int,
    ) : NewInstanceFactory() {

        @Suppress("SingletonConstructor")
        constructor() : this(null, 0)

        /**
         * Constructs this factory.
         *
         * @param application an application to pass in [AndroidViewModel]
         */
        @Suppress("SingletonConstructor")
        constructor(application: Application) : this(application, 0)

        @Suppress("DocumentExceptions")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            return if (application != null) {
                create(modelClass)
            } else {
                val application = extras[APPLICATION_KEY]
                if (application != null) {
                    create(modelClass, application)
                } else {
                    // For AndroidViewModels, CreationExtras must have an application set
                    if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
                        throw IllegalArgumentException(
                            "CreationExtras must have an application by `APPLICATION_KEY`"
                        )
                    }
                    super.create(modelClass)
                }
            }
        }

        @Suppress("DocumentExceptions")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (application == null) {
                throw UnsupportedOperationException(
                    "AndroidViewModelFactory constructed " +
                            "with empty constructor works only with " +
                            "create(modelClass: Class<T>, extras: CreationExtras)."
                )
            } else {
                create(modelClass, application)
            }
        }

        @Suppress("DocumentExceptions")
        private fun <T : ViewModel> create(
            modelClass: Class<T>,
            app: Application
        ): T {
            return if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
                try {
                    modelClass.getConstructor(Application::class.java).newInstance(app)
                } catch (e: NoSuchMethodException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                } catch (e: IllegalAccessException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                } catch (e: InstantiationException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                } catch (e: InvocationTargetException) {
                    throw RuntimeException("Cannot create an instance of $modelClass", e)
                }
            } else super.create(modelClass)
        }

        companion object {
            internal fun defaultFactory(owner: ViewModelStoreOwner): Factory =
                if (owner is HasDefaultViewModelProviderFactory)
                    owner.defaultViewModelProviderFactory else instance

            internal const val DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey"

            private var sInstance: AndroidViewModelFactory? = null

            /**
             * Retrieve a singleton instance of AndroidViewModelFactory.
             *
             * @param application an application to pass in [AndroidViewModel]
             * @return A valid [AndroidViewModelFactory]
             */
            @JvmStatic
            fun getInstance(application: Application): AndroidViewModelFactory {
                if (sInstance == null) {
                    sInstance = AndroidViewModelFactory(application)
                }
                return sInstance!!
            }

            private object ApplicationKeyImpl : CreationExtras.Key<Application>

            /**
             * A [CreationExtras.Key] to query an application in which ViewModel is being created.
             */
            @JvmField
            val APPLICATION_KEY: CreationExtras.Key<Application> = ApplicationKeyImpl
        }
    }

    /**
     * 定义该类的作用？
     */
    open class OnRequeryFactory {
        open fun onRequery(viewModel: ViewModel) {}
    }

    @MainThread
    open operator fun <T : ViewModel> get(modelClass: Class<T>): T {
        val canonicalName = modelClass.canonicalName
            ?: throw IllegalArgumentException("Local and anonymous classes can not be ViewModels")
        return get("$DEFAULT_KEY:$canonicalName", modelClass)
    }

    @MainThread
    open operator fun <T : ViewModel> get(key: String, modelClass: Class<T>): T {
        val viewModel = store[key]
        if (modelClass.isInstance(viewModel)) {
            (factory as? OnRequeryFactory)?.onRequery(viewModel!!)
            return viewModel as T
        } else {
            @Suppress("ControlFlowWithEmptyBody")
            if (viewModel != null) {
                // TODO: log a warning.
            }
        }
        val extras = MutableCreationExtras(defaultCreationExtras)
        extras[NewInstanceFactory.VIEW_MODEL_KEY] = key
        // AGP has some desugaring issues associated with compileOnly dependencies so we need to
        // fall back to the other create method to keep from crashing.
        return try {
            factory.create(modelClass, extras)
        } catch (e: AbstractMethodError) {
            factory.create(modelClass)
        }.also { store.put(key, it) }

    }

}

internal fun defaultCreationExtras(owner: ViewModelStoreOwner): CreationExtras {
    return if (owner is HasDefaultViewModelProviderFactory) {
        owner.defaultViewModelCreationExtras
    } else CreationExtras.Empty
}

@MainThread
inline fun <reified VM : androidx.lifecycle.ViewModel> ViewModelProvider.get(): VM =
    get(VM::class.java)
