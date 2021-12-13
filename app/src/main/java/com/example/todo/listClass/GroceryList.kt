package com.example.todo.listClass

import com.example.todo.data.Task

class GroceryList(val list: List<Task>) {
    constructor() : this(ArrayList())
}