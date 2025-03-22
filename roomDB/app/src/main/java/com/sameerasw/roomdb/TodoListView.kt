package com.sameerasw.roomdb

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sameerasw.roomdb.db.Todo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource

class TodoListView
@Composable
fun TodoListView(viewModel: TodoVM, modifier: Modifier = Modifier) {
    val todoList by viewModel.todoItems.observeAsState()

    var inputText by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Row {
            TodoInputField(
                value = inputText,
                onValueChange = { newValue -> inputText = newValue },
                onAddItem = { newItem ->
                    viewModel.addTodoItem(newItem)
                    inputText = ""
                }
            )
        }

        Column(
            modifier = Modifier.weight(1f).padding(top = 16.dp)
        ) {
            todoList?.let { todos ->
                LazyColumn {
                    items(todos) { todo ->
                        TodoItemView(todo = todo) {
                            viewModel.deleteTodoItem(it.id)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodoInputField(value: String, onValueChange: (String) -> Unit, onAddItem: (String) -> Unit) {

    Row {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f).padding(end = 16.dp)
        )
        Button(
            onClick = { onAddItem(value) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Add",
            )
            Text("Add")
        }
    }
}

@Composable
fun TodoItemView(todo: Todo, onDeleteItem: (Todo) -> Unit) {
    Row {
        Text(todo.title, modifier = Modifier.weight(1f))
        IconButton(onClick = { onDeleteItem(todo) }) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete"
            )
        }
    }
}