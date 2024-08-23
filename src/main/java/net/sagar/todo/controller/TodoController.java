package net.sagar.todo.controller;

import lombok.AllArgsConstructor;
import net.sagar.todo.dto.TodoDto;
import net.sagar.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todo")
public class TodoController {

    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> creteTodo(@RequestBody TodoDto todoDto){
       TodoDto saved= todoService.addTodo(todoDto);
       return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getById(@PathVariable("id") Long todoId){
        TodoDto data=todoService.getTodoById(todoId);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllRecords(){
        List<TodoDto> allTodos=todoService.getAllTodo();
        return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
        TodoDto updated=todoService.updateTodo(todoDto,todoId);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long Id){
        todoService.deleteTodoByID(Id);
        return new ResponseEntity<>("Record delete successfully..!!",HttpStatus.OK);
    }

    @PatchMapping("/{id}/complete")//this http Request is not used now day
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id){
        TodoDto todoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    @PatchMapping("/{id}/incomplete")//this http Request is not used now day
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id){
        TodoDto todoDto = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(todoDto);
    }
}
