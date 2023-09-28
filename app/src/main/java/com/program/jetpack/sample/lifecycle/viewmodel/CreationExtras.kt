package com.program.jetpack.sample.lifecycle.viewmodel

abstract class CreationExtras internal constructor() {
    interface Key<T>

    internal val map: MutableMap<Key<*>, Any?> = mutableMapOf()
    abstract operator fun <T> get(key: Key<T>): T?

    object Empty : CreationExtras() {
        override fun <T> get(key: Key<T>): T? = null

    }
}

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

