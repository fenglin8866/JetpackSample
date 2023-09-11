package com.program.language.kotlinstudy

import androidx.compose.runtime.Composable
import com.program.common.LanguagePrintScreen
import com.program.common.utils.XLog

@Composable
fun DelegateTestScreen() {
    LanguagePrintScreen {
        val d=DelegateBaseImap(BaseImp())
        d.message()
        d.messageLine()
    }
}

interface Base {
    val message: String
    fun message();
    fun messageLine()
}

class BaseImp() : Base {
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


