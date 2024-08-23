package net.sagar.todo.service;

import net.sagar.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodoById(Long Id);

    List<TodoDto> getAllTodo();

    TodoDto updateTodo(TodoDto todoDto,Long Id);

    void deleteTodoByID(Long Id);

    TodoDto completeTodo(Long todoId);

    TodoDto inCompleteTodo(Long todoId);
}
