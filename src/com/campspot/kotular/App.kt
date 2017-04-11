package com.campspot.kotular

import com.campspot.kotular.angular.Location
import com.campspot.kotular.angular.Timeout
import com.campspot.kotular.angular.angular

/**
 * Created by bradensteffaniak on 4/11/17.
 */

external interface FilterFilter {
    operator fun invoke(data: Array<Todo>, completed: Boolean): Array<Todo>
}

fun main(args: Array<String>) {
    var todomvc = angular.module("todomvc", array())
    todomvc.factory("todoStorage", array<Any>({ TodoStorage() }))
    todomvc.directive("todoFocus", array<Any>("\$timeout", {x: Timeout -> todoFocus(x) }))
    todomvc.controller("TodoCtrl", array<Any>("\$scope", "\$location", "todoStorage", "filterFilter", {
        a: TodoScope, b: Location, c: TodoStorage, d: FilterFilter ->
        TodoCtrl(a, b, c, d)
    }))
}

external fun <X> array(vararg x: Any): Array<X>