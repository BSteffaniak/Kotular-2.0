package com.campspot.kotular

import com.campspot.kotular.angular.Directive
import com.campspot.kotular.angular.Timeout

/**
 * Created by bradensteffaniak on 4/11/17.
 */


external interface Attrs {
    val todoBlur: Any
    val todoFocus: Any
}

fun todoFocus(timeout: Timeout): Directive {
    val directive = Directive()
    directive.link = { scope, elem, _attrs ->
        val attrs = _attrs as Attrs
        scope.watch(attrs.todoFocus, { newVal ->
            if(newVal as Boolean) {
                js("timeout(function () { elem[0].focus() }, 0, false)")
            }
        })
    }
    return directive
}

fun todoBlur(): Directive {
    val directive = Directive()
    directive.link = { scope, elem, _attrs ->
        val attrs = _attrs as Attrs
        scope.apply(attrs.todoBlur)
    }
    return directive
}