package com.campspot.kotular

import com.campspot.kotular.*

/**
 * Created by bradensteffaniak on 4/11/17.
 */

class TodoStorage {
    private val STORAGE_ID = "TODOS-angularjs"

    fun get(): JsArray<Todo> {
        var data = localStorage.getItem(STORAGE_ID)
        if(data == null) {
            data = "[]"
        }
        return JSON.parse<String>(data!!) as JsArray<Todo>
    }

    fun put(data: Array<Todo>) {
        localStorage.setItem(STORAGE_ID, JSON.stringify(data))
    }
}