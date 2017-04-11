package com.campspot.kotular.angular

/**
 * Created by bradensteffaniak on 4/11/17.
 */

external interface Angular {
    fun module(name: String, deps: Array<String>): Module
}

external interface Module {
    fun directive(name: String, injectsAndDef: Array<Any>): Unit
    fun factory (name: String, injectsAndDef: Array<Any>): Unit
    fun controller(name: String, injectsAndDef: Array<Any>): Unit
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