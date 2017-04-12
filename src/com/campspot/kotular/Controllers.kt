package com.campspot.kotular

import com.campspot.kotular.angular.*
import com.campspot.kotular.js.JsArray

/**
 * Created by bradensteffaniak on 4/11/17.
 */

data class Todo(var title: String? = "", var completed: Boolean = false)

class TodoCtrl {
    var todos: JsArray<Todo> = JsArray()
    var newTodo: String = ""
    var editedTodo: Todo? = null
    var remainingCount: Int = 0
    var completedCount: Int = 0
    var allChecked: Boolean = false
    var statusFilter: Boolean? = false
    var location: Location? = null
    var test: String = "jello"

    constructor(scope: Scope, location: Location, todoStorage: TodoStorage, filterFilter: (JsArray<Todo>, Boolean) -> JsArray<Todo>) {
        todos = todoStorage.get()

        scope.watch("todos", {
            remainingCount = filterFilter(todos, false).size
            completedCount = todos.size - remainingCount
            completedCount = todos.size - remainingCount
            allChecked = remainingCount == 0
        }, true)

        if(location.path() == "") {
            location.path("/")
        }

        this.location = location

        scope.watch("location.path()", {
            path ->
            statusFilter = when(path) {
                "/active" -> false
                "/completed" -> true
                else -> null
            }
        })
    }

    fun addTodo() {
        if(newTodo.isNotEmpty()) {
            todos.push(Todo(newTodo, false))
            newTodo = ""
        }
    }

    fun editTodo(todo: Todo) {
        editedTodo = todo
    }

    fun doneEditing(todo: Todo) {
        editedTodo = null
        if(todo.title != null) {
            removeTodo(todo)
        }
    }

    fun removeTodo(todo: Todo) {
        todos.splice(todos.indexOf(todo), 1)
    }

    fun clearCompletedTodos() {
        todos = todos.filter({ !it.completed })
    }

    fun markAll(completed: Boolean) {
        todos.forEach({ it.completed = completed })
    }
}