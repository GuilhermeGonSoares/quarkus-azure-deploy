package azure.deploy.repositories;

import azure.deploy.entities.Todo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TodoRepository {
    private static Long NEXT_ID = 4L;
    private final List<Todo> todos = new ArrayList<>();

    public TodoRepository() {
        todos.add(new Todo(1L, "Learn Spring Boot", true));
        todos.add(new Todo(2L, "Learn Azure", false));
        todos.add(new Todo(3L, "Deploy Quarkus to Azure", false));
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo findById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Todo not found"));
    }

    public Todo save(String name) {
        Todo todo = new Todo(NEXT_ID++, name, false);
        todos.add(todo);
        return todo;
    }

    public Todo update(Long id, String name) {
        Todo existingTodo = findById(id);
        if (existingTodo.isCompleted())
            throw new BadRequestException("Cannot update completed todo");

        existingTodo.setName(name);
        return existingTodo;
    }

    public Todo complete(Long id) {
        Todo existingTodo = findById(id);
        if (existingTodo.isCompleted())
            throw new BadRequestException("Todo already completed");
        existingTodo.setCompleted(true);
        return existingTodo;
    }

    public void delete(Long id) {
        Todo existingTodo = findById(id);
        todos.remove(existingTodo);
    }
}
