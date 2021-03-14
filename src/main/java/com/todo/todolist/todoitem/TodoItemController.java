package com.todo.todolist.todoitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/todoitem")
public class TodoItemController {
    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemController(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @PostMapping(path="/add")
    public @ResponseBody Integer addNewTodoItem(@RequestParam String title, @RequestParam Integer categoryId) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(title);
        todoItem.setCategoryId(categoryId);
        todoItem.setCompleted(false);
        todoItemRepository.save(todoItem);
        return todoItem.getId();
    }

    @PostMapping(path="/update/{todoitemId}")
    public @ResponseBody String updateTodoItem(@RequestParam String title, @RequestParam Boolean completed,
                                             @PathVariable Integer todoitemId) {
        Optional<TodoItem> todoItemOptional = todoItemRepository.findById(todoitemId);
        if (todoItemOptional.isEmpty()) {
            return "0";
        }

        TodoItem todoItem = todoItemOptional.get();
        todoItem.setTitle(title);
        todoItem.setCompleted(completed);
        todoItemRepository.save(todoItem);
        return "1";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<TodoItem> getAllTodoItems() {
        return todoItemRepository.findAll();
    }

    @GetMapping(path="/all/{categoryId}")
    public @ResponseBody Iterable<TodoItem> getAllTodoItemsByCategoryId(@PathVariable Integer categoryId) {
        return todoItemRepository.findAllByCategoryId(categoryId);
    }

    @GetMapping(path="/all/completed/{categoryId}")
    public @ResponseBody Iterable<TodoItem> getAllTodoItemsByCategoryIdCompleted(@PathVariable Integer categoryId) {
        return todoItemRepository.findAllByCategoryIdAndCompletedIsTrue(categoryId);
    }

    @GetMapping(path="/all/notcompleted/{categoryId}")
    public @ResponseBody Iterable<TodoItem> getAllTodoItemsByCategoryIdNotCompleted(@PathVariable Integer categoryId) {
        return todoItemRepository.findAllByCategoryIdAndCompletedIsFalse(categoryId);
    }
}
