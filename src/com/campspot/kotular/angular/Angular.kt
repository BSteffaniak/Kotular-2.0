package com.campspot.kotular.angular

import com.campspot.kotular.js.JsArray

/**
 * Created by bradensteffaniak on 4/11/17.
 */

external interface Angular {
    fun module(name: String, deps: JsArray<String>): Module
}

external interface Module {
    fun directive(name: String, injectsAndDef: JsArray<Any>): Unit
    fun factory (name: String, injectsAndDef: JsArray<Any>): Unit
    fun controller(name: String, injectsAndDef: JsArray<Any>): Unit
    fun config(injectsAndDef: JsArray<Any>): Unit
    fun filter(name: String, lambda: Any): Unit
}

external interface ElemNode {
    fun focus()
}

external interface Elem {
    fun bind(name: String, func: () -> Unit)
    fun get(index: Int): ElemNode
}

external class Directive {
    var link: (scope: Scope, elem: Elem, attrs: Any) -> Unit
}

external interface Timeout {
    fun invoke(func: () -> Unit, x: Int, y: Boolean)
}

external interface Location {
    fun path(): String
    fun path(p: String)
}

external interface Scope {
    fun `$watch`(exp: Any, todo: (Any?) -> Unit, deepWatch: Boolean)
    fun `$watch`(exp: Any, todo: (Any?) -> Unit)
    fun `$apply`(func: Any): Unit
}

external interface LocalStorage {
    fun getItem(id: String): String?
    fun setItem(id: String, data: String)
}

external val angular: Angular