package net.sagar.todo.service;

import lombok.AllArgsConstructor;
import net.sagar.todo.dto.TodoDto;
import net.sagar.todo.entity.Todo;
import net.sagar.todo.exception.ResourceNotFoundException;
import net.sagar.todo.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert TodoDto into Todo jpa entity
        Todo todo=modelMapper.map(todoDto,Todo.class);

       //todo jpa entity
       Todo savedTodo= todoRepository.save(todo);

       //convert Todo jpa entity to TodoDto class
        TodoDto savedTodoDto=modelMapper.map(savedTodo,TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodoById(Long Id) {
       Todo byId= todoRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("this id invalid "+Id));
       TodoDto todoData=modelMapper.map(byId,TodoDto.class);
        return todoData;
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> allRecords=todoRepository.findAll();
        return allRecords.stream().map((todo)->modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long Id) {
        Todo todos= todoRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("id not found the "+Id));
        todos.setTitle(todoDto.getTitle());
        todos.setCompleted(todoDto.getCompleted());
        todos.setDescription(todoDto.getDescription());
       Todo updateTodo= todoRepository.save(todos);
        return modelMapper.map(updateTodo,TodoDto.class);
    }

    @Override
    public void deleteTodoByID(Long Id) {
        Todo todo=todoRepository.findById(Id).orElseThrow(()->new ResourceNotFoundException("todo not found with id : "+Id));

        todoRepository.deleteById(Id);
    }

    @Override
    public TodoDto completeTodo(Long todoId) {

     Todo updated= todoRepository.findById(todoId)
             .orElseThrow(()->new ResourceNotFoundException("not found the id : "+todoId));
     updated.setCompleted(Boolean.TRUE);
     todoRepository.save(updated);
        return modelMapper.map(updated,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long todoId) {

        Todo updated= todoRepository.findById(todoId)
                .orElseThrow(()->new ResourceNotFoundException("not found the id : "+todoId));
        updated.setCompleted(Boolean.FALSE);
        todoRepository.save(updated);
        return modelMapper.map(updated,TodoDto.class);
    }
}
