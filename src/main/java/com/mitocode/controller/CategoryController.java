package com.mitocode.controller;

import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @GetMapping
    public List<Category> readAll() throws Exception{
        return service.readAll();
    }

    @GetMapping("idCategory ")
    public Category readById(@PathVariable Integer idCategory) throws Exception{
        return service.readById(idCategory);
    }

    @PostMapping
    public Category create(@RequestBody Category category) throws Exception {
        return service.save(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable("id") Integer id, @RequestBody Category category) throws Exception {
        category.setIdCategory(id);
        return service.save(category);
    }
    @DeleteMapping("/{idCategory}")
    public void delete(@PathVariable Integer idCategory) throws Exception {
         service.delete(idCategory);
    }



}
