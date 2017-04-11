package com.campspot.kotular.js

/**
 * Created by bradensteffaniak on 4/11/17.
 */

//external fun <T> Array<T>.push(x: T)
//fun <T> Array<T>.splice(i1: Int, i2: Int) = definedExternally
//fun <T> Array<T>.indexOf(x: T) = definedExternally
//fun <T> Array<T>.filter(x: (T)->Boolean): Array<T> = definedExternally
//fun <T> Array<T>.forEach(x: (T)->Unit) = definedExternally
//val <T> Array<T>.length: Int = definedExternally

@JsName("Array")
external class JsArray<T> {
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