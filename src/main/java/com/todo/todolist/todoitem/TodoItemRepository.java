package com.todo.todolist.todoitem;

import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {
    Iterable<TodoItem> findAllByCategoryId(Integer categoryId);

    Iterable<TodoItem> findAllByCategoryIdAndCompletedIsFalse(Integer categoryId);

    Iterable<TodoItem> findAllByCategoryIdAndCompletedIsTrue(Integer categoryId);
}
