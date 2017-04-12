package com.campspot.kotular

import com.campspot.kotular.angular.Route
import com.campspot.kotular.angular.Router
import com.campspot.kotular.angular.angular
import com.campspot.kotular.js.JsArray

/**
 * Created by bradensteffaniak on 4/11/17.
 */

object FilterFilter {
    fun filter(data: JsArray<Todo>, completed: Boolean): JsArray<Todo> {
        return data.filter { it.completed == completed }
    }
}

fun main(args: Array<String>) {
    val todomvc = angular.module("todomvc", JsArray("ngRoute"))

    todomvc.filter("filter", { FilterFilter::filter })
    todomvc.factory("todoStorage", JsArray({ TodoStorage() }))
    todomvc.directive("todoFocus", JsArray("\$timeout", ::todoFocus))
    todomvc.controller("TodoCtrl", JsArray("\$scope", "\$location", "todoStorage", "filterFilter", ::TodoCtrl))

    todomvc.config(JsArray("\$routeProvider", {
        routeProvider: Router ->
        routeProvider.route("/test", Route("templates/test.html", "TodoCtrl", "ctrl"))
    }))
}