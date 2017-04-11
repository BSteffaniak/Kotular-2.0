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
}

fun TodoCtrl(scope: TodoScope, location: Location, todoStorage: TodoStorage, filterFilter: FilterFilter) {

    scope.todos = todoStorage.get()
    scope.newTodo = ""
    scope.editedTodo = null

    scope.`$watch`("todos", {
        scope.remainingCount = filterFilter(scope.todos, false).size
        scope.completedCount = scope.todos.size - scope.remainingCount
        scope.completedCount = scope.todos.size - scope.remainingCount
        scope.allChecked = scope.remainingCount == 0
    }, true)

    if(location.path() == "") {
        location.path("/")
    }

    scope.location = location

    scope.`$watch`("location.path()", { path ->
        scope.statusFilter = when(path) {
            "/active" -> false
            "/completed" -> true
            else -> null
        }
    })

    scope.addTodo = {
        if(scope.newTodo.length > 0) {
            val todo = Todo()
            todo.title = scope.newTodo
            todo.completed = false
            js("scope.todos.push(todo)")
            scope.newTodo = ""
        }
    }

    scope.editTodo = { todo ->
        scope.editedTodo = todo
    }

    scope.doneEditing = { todo ->
        scope.editedTodo = null
        if(todo.title != null) {
            scope.removeTodo(todo)
        }
    }

    scope.removeTodo = { todo ->
        js("scope.todos.splice(scope.todos.indexOf(todo), 1)")
    }

    scope.clearCompletedTodos = {
        js("scope.todos = scope.todos.filter(function (it) { !it.completed })")
    }

    scope.markAll = { completed ->
        scope.todos.forEach({ it.completed = completed })
    }
}