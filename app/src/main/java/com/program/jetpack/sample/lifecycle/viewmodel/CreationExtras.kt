package com.program.jetpack.sample.lifecycle.viewmodel

/**
 * 存储数据：存储传入ViewModel的数据
 * 疑问点：为什么定义Key作为map的key？
 */
abstract class CreationExtras internal constructor() {
    interface Key<T>

    internal val map: MutableMap<Key<*>, Any?> = mutableMapOf()
    abstract operator fun <T> get(key: Key<T>): T?

    object Empty : CreationExtras() {
        override fun <T> get(key: Key<T>): T? = null

    }
}

/**
 * 数据set/get
 * 数据对象可以反复嵌套。全部存储在第一个对象内。
 */
class MutableCreationExtras(initialExtras: CreationExtras = Empty) : CreationExtras() {

    init {
        map.putAll(initialExtras.map)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: Key<T>): T? {
        return map[key] as T?
    }

    operator fun <T> set(key: Key<T>, t: T) {
        map[key] = t
    }

}

