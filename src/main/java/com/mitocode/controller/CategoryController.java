package com.mitocode.controller;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

//    @GetMapping
//    public List<Category> readAll() throws Exception{
//        return service.readAll();
//    }

    @GetMapping
    public ResponseEntity<List<Category>> readAll() throws Exception{
        List<Category> lst = service.readAll();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> readById(@PathVariable ("id") Integer id) throws Exception{
        Category obj = service.readById(id);

        if (obj == null){
            throw  new ModelNotFoundException("ID NO ENCONTRADO" + id);
        }

        return new ResponseEntity<>(obj,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) throws Exception {
        Category obj = service.save(category);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category>update(@RequestBody Category category) throws Exception{
        Category obj = service.update(category);
        return new ResponseEntity<>(obj,HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public Category update(@PathVariable("id") Integer id, @RequestBody Category category) throws Exception {
//        category.setIdCategory(id);
//        return service.save(category);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")  Integer id) throws Exception {
         service.delete(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

}
