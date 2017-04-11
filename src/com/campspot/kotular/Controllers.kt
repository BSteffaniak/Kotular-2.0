package com.campspot.kotular

import com.campspot.kotular.angular.*;
import com.campspot.kotular.js.JsArray

/**
 * Created by bradensteffaniak on 4/11/17.
 */

external class Todo {
    var title: String?
    var completed: Boolean
}

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
    val ctrl = js("this") as TodoScope

    ctrl.todos = todoStorage.get()
    ctrl.newTodo = ""
    ctrl.editedTodo = null
    ctrl.test = "jello"

    scope.watch("todos", {
        scope.remainingCount = filterFilter(scope.todos, false).size
        scope.completedCount = scope.todos.size - scope.remainingCount
        scope.completedCount = scope.todos.size - scope.remainingCount
        scope.allChecked = scope.remainingCount == 0
    }, true)

    if(location.path() == "") {
        location.path("/")
    }

    ctrl.location = location

    scope.watch("location.path()", { path ->
        scope.statusFilter = when(path) {
            "/active" -> false
            "/completed" -> true
            else -> null
        }
    })

    ctrl.addTodo = {
        if(scope.newTodo.isNotEmpty()) {
            scope.todos.push(Todo(scope.newTodo, false))
            scope.newTodo = ""
        }
    }

    ctrl.editTodo = { todo ->
        scope.editedTodo = todo
    }

    ctrl.doneEditing = { todo ->
        scope.editedTodo = null
        if(todo.title != null) {
            scope.removeTodo(todo)
        }
    }

    ctrl.removeTodo = { todo ->
        scope.todos.splice(scope.todos.indexOf(todo), 1)
    }

    ctrl.clearCompletedTodos = {
        scope.todos = scope.todos.filter({ !it.completed })
    }

    ctrl.markAll = { completed ->
        scope.todos.forEach({ it.completed = completed })
    }
}