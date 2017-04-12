package com.campspot.kotular

/**
 * Created by bradensteffaniak on 4/12/17.
 */

@JsName("Array")
external interface JsArray<T> {
    fun push(obj: T): T
    fun splice(i1: Int, i2: Int): JsArray<T>
    fun indexOf(obj: T): Int
    fun forEach(action: (T) -> Unit)
    fun <X> map(action: (T) -> X): JsArray<X>
    fun filter(action: (T) -> Boolean): JsArray<T>
    @JsName("length") val size: Int
}

@JsName("Array")
external fun <X> JsArray(vararg x: Any): JsArray<X>

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

data class Route(val templateUrl: String, val controller: String, val controllerAs: String)

external interface Router {
    @JsName("when") fun route(url: String, route: Route): Router
}

external interface ElemNode {
    fun focus()
}

external interface Elem {
    fun bind(name: String, func: () -> Unit)
    operator fun get(index: Int): ElemNode
}

external class Directive {
    var link: (scope: Scope, elem: Elem, attrs: Any) -> Unit
}

external interface Timeout {
    operator fun invoke(func: () -> Unit, x: Int, y: Boolean)
}

external interface Location {
    fun path(): String
    fun path(p: String)
}

external interface Scope {
    @JsName("\$watch") fun watch(exp: Any, todo: (Any?) -> Unit, deepWatch: Boolean)
    @JsName("\$watch") fun watch(exp: Any, todo: (Any?) -> Unit)
    @JsName("\$apply") fun apply(func: Any): Unit
}

external interface LocalStorage {
    fun getItem(id: String): String?
    fun setItem(id: String, data: String)
}

external val angular: Angular
@JsName("this") external val controllerScope: Scope

external val localStorage: LocalStorage