package com.campspot.kotular

import com.campspot.kotular.angular.*
import com.campspot.kotular.js.JsArray

/**
 * Created by bradensteffaniak on 4/11/17.
 */

data class Todo(var title: String? = "", var completed: Boolean = false)

external interface TodoScope : Scope {
    var todos: JsArray<Todo>
    var newTodo: String
    var editedTodo: Todo?
    var remainingCount: Int
    var completedCount: Int
    var allChecked: Boolean
    var statusFilter: Boolean?
    var location: Location
    var addTodo: () -> Unit
    var editTodo: (Todo) -> Unit
    var doneEditing: (Todo) -> Unit
    var removeTodo: (Todo) -> Unit
    var clearCompletedTodos: () -> Unit
    var markAll: (Boolean) -> Unit
    var test: String
}

fun TodoCtrl(scope: TodoScope, location: Location, todoStorage: TodoStorage, filterFilter: (JsArray<Todo>, Boolean) -> JsArray<Todo>) {
    val ctrl = controllerScope as TodoScope

    ctrl.todos = todoStorage.get()
    ctrl.newTodo = ""
    ctrl.editedTodo = null
    ctrl.test = "jello"

    scope.watch("todos", {
        ctrl.remainingCount = filterFilter(ctrl.todos, false).size
        ctrl.completedCount = ctrl.todos.size - ctrl.remainingCount
        ctrl.completedCount = ctrl.todos.size - ctrl.remainingCount
        ctrl.allChecked = ctrl.remainingCount == 0
    }, true)

    if(location.path() == "") {
        location.path("/")
    }

    ctrl.location = location

    scope.watch("location.path()", {
        path ->
        ctrl.statusFilter = when(path) {
            "/active" -> false
            "/completed" -> true
            else -> null
        }
    })

    ctrl.addTodo = {
        if(ctrl.newTodo.isNotEmpty()) {
            ctrl.todos.push(Todo(ctrl.newTodo, false))
            ctrl.newTodo = ""
        }
    }

    ctrl.editTodo = {
        todo ->
        ctrl.editedTodo = todo
    }

    ctrl.doneEditing = {
        todo ->
        ctrl.editedTodo = null
        if(todo.title != null) {
            ctrl.removeTodo(todo)
        }
    }

    ctrl.removeTodo = {
        todo ->
        ctrl.todos.splice(ctrl.todos.indexOf(todo), 1)
    }

    ctrl.clearCompletedTodos = {
        ctrl.todos = ctrl.todos.filter({ !it.completed })
    }

    ctrl.markAll = {
        completed ->
        ctrl.todos.forEach({ it.completed = completed })
    }
}