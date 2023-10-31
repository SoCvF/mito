package com.mitocode.service;

import com.mitocode.model.Category;

import java.util.List;

public interface ICategoryService {

    Category save(Category category) throws Exception;
    Category update(Category category) throws Exception;
    List<Category> readAll() throws Exception;
    Category readById(Integer id) throws Exception;
    void delete(Integer id)throws Exception;
}
