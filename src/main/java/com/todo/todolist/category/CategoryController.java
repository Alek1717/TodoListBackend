package com.todo.todolist.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping(path="/add")
    public @ResponseBody Integer addNewCategory(@RequestParam String title) {
        Category category = new Category();
        category.setTitle(title);
        categoryRepository.save(category);
        return category.getId();
    }

    @PostMapping(path="/update/{categoryId}")
    public @ResponseBody String updateCategory(@RequestParam String title, @PathVariable Integer categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            return "0";
        }

        Category category = categoryOptional.get();
        category.setTitle(title);
        categoryRepository.save(category);
        return "1";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
