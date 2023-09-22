package com.program.language.kotlinstudy.delegate

import androidx.compose.runtime.Composable
import com.program.common.LanguagePrintScreen
import com.program.common.utils.XLog
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

@Composable
fun DelegateTestScreen() {
    LanguagePrintScreen {
        val d = DelegateBaseImap(BaseImp())
        d.message()
        d.messageLine()
    }
}

interface Base {
    val message: String
    fun message();
    fun messageLine()
}

class BaseImp : Base {
    override val message: String
        get() = "BaseImp"

    override fun message() {
        XLog.d(message)
    }

    override fun messageLine() {
        XLog.d("$message line")
    }

}

class DelegateBaseImap(b: BaseImp) : Base by b {

    override val message: String
        get() = "DelegateBaseImap"

    override fun messageLine() {
        XLog.d("$message line")
    }
}

class DelegateBaseImap2() : Base by BaseImp() {

    override val message: String
        get() = "DelegateBaseImap"

    override fun messageLine() {
        XLog.d("$message line")
    }
}

/**
 * 委托属性定义
 */
class DelegateProperty {
    var suc by Delegate()
    var fail by Delegates
    val err by lazy {

    }
}

private operator fun Delegates.setValue(
    delegateProperty: DelegateProperty,
    property: KProperty<*>,
    any: Any
) {
    TODO("Not yet implemented")
}

private operator fun Delegates.getValue(
    delegateProperty: DelegateProperty,
    property: KProperty<*>
): Any {
    TODO("Not yet implemented")
}

class Delegate {
    operator fun setValue(
        delegatePro: DelegateProperty,
        property: KProperty<*>,
        any: Any
    ) {
        TODO("Not yet implemented")
    }

    operator fun getValue(delegatePro: DelegateProperty, property: KProperty<*>): Any {
        TODO("Not yet implemented")
    }
}






