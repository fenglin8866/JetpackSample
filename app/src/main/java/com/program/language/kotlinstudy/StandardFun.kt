package com.program.language.kotlinstudy

class StandardFun {
}

/**
 * 扩展函数，
 * 作用：需要调用该对象的属性或方法
 * 作用域：是该对象内部
 */
fun <T> T.apply(block: T.() -> Unit): T {
    block()
    return this
}


/**
 * 扩展函数，
 * 作用：使用该对象
 * 作用域：外层对象
 */
fun <T> T.also(block: (T) -> Unit): T {
    block(this)
    return this
}