package net.sagar.todo.repository;

import net.sagar.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
