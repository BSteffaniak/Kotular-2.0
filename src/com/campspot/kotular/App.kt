package com.campspot.kotular

import com.campspot.kotular.angular.Location
import com.campspot.kotular.angular.Timeout
import com.campspot.kotular.angular.angular
import com.campspot.kotular.js.JsArray

/**
 * Created by bradensteffaniak on 4/11/17.
 */

external interface FilterFilter {
    operator fun invoke(data: Array<Todo>, completed: Boolean): Array<Todo>
}

fun main(args: Array<String>) {
    val todomvc = angular.module("todomvc", JsArray())
    todomvc.filter("filter", { FilterFilter::filter })
    todomvc.factory("todoStorage", JsArray({ TodoStorage() }))
    todomvc.directive("todoFocus", JsArray("\$timeout", ::todoFocus))
    todomvc.controller("TodoCtrl", JsArray("\$scope", "\$location", "todoStorage", "filterFilter", ::TodoCtrl))

