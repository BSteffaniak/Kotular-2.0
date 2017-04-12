package com.campspot.kotular

import com.campspot.kotular.*

/**
 * Created by bradensteffaniak on 4/11/17.
 */


interface Attrs {
    val todoBlur: Any
    val todoFocus: Any
}

fun todoFocus(timeout: Timeout): Directive {
    val directive = Directive()

    directive.link = {
        scope, elem, _attrs ->
        val attrs = _attrs as Attrs
        scope.watch(attrs.todoFocus, { newVal ->
            if(newVal as Boolean) {
                timeout({ elem[0].focus() }, 0, false)
            }
        })
    }
    
    return directive
}

fun todoBlur(): Directive {
    val directive = Directive()
    directive.link = {
        scope, elem, _attrs ->
        val attrs = _attrs as Attrs
        scope.apply(attrs.todoBlur)
    }
    return directive
}